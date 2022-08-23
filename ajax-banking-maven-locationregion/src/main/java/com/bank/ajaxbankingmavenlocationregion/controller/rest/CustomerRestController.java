package com.bank.ajaxbankingmavenlocationregion.controller.rest;

import com.bank.ajaxbankingmavenlocationregion.model.*;
import com.bank.ajaxbankingmavenlocationregion.model.dto.CustomerDTO;
import com.bank.ajaxbankingmavenlocationregion.model.dto.DepositDTO;
import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferDTO;
import com.bank.ajaxbankingmavenlocationregion.model.dto.WithdrawDTO;
import com.bank.ajaxbankingmavenlocationregion.service.customer.CustomerService;
import com.bank.ajaxbankingmavenlocationregion.service.deposit.DepositService;
import com.bank.ajaxbankingmavenlocationregion.service.location.LocationRegionService;
import com.bank.ajaxbankingmavenlocationregion.service.transfer.TransferService;
import com.bank.ajaxbankingmavenlocationregion.service.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocationRegionService locationRegionService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerDTO> customers = customerService.findAllCustomerDTO ();

        if (customers.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@RequestBody CustomerDTO customerDTO) {
        customerDTO.getLocationRegion().setId(0L);
        LocationRegion locationRegion = locationRegionService.save(customerDTO.getLocationRegion().toLocationRegion());

        customerDTO.setLocationRegion(locationRegion.toLocationRegionDTO());
        customerDTO.setId(0L);
        customerDTO.setBalance(new BigDecimal (0L));

        Customer customer = customerDTO.toCustomer();

        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer.toCustomerDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@RequestBody CustomerDTO customerDTO) {

        LocationRegion locationRegion = locationRegionService.save(customerDTO.getLocationRegion().toLocationRegion());

        customerDTO.setLocationRegion(locationRegion.toLocationRegionDTO());

        Customer customer = customerDTO.toCustomer();

        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer.toCustomerDTO(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> doDeposit(@Validated @RequestBody DepositDTO depositDTO, BindingResult bindingResult) {
        Optional<Customer> customerOptional = customerService.findById ( depositDTO.getCustomerId () );
        if ( customerOptional.isPresent () ) {
            Deposit deposit = depositDTO.toDeposit ( customerOptional.get () );

            Customer customer = depositService.doDeposit ( deposit );

            return new ResponseEntity<> ( customer.toCustomerDTO (), HttpStatus.ACCEPTED );

        }
        return null;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> doWithdraw(@RequestBody WithdrawDTO withdrawDTO) {
        Optional<Customer> customerOptional = customerService.findById ( withdrawDTO.getCustomerId () );
        if ( customerOptional.isPresent () ) {
            Customer customer = customerOptional.get ();
            Withdraw withdraw = withdrawDTO.toDeposit ( customer );

            withdrawService.save ( withdraw );

            BigDecimal newBalance = customer.getBalance ().subtract ( withdraw.getTransactionAmount () );

            customer.setBalance ( newBalance );

            Customer newCustomer = customerService.save ( customer );

            return new ResponseEntity<> ( newCustomer.toCustomerDTO (), HttpStatus.ACCEPTED );

        }
        return null;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> doTransfer(@RequestBody TransferDTO transferDTO) {

        Optional<Customer> sender = customerService.findById ( transferDTO.getSenderId () );
        if ( sender.isPresent () ) {
            Optional<Customer> recipient = customerService.findById ( transferDTO.getRecipientId () );
            Customer senderCustomer = sender.get ();
            Customer recipientCustomer = recipient.get ();

            Transfer transfer = transferDTO.toTransfer ( senderCustomer, recipientCustomer );


            BigDecimal currentBalanceSender = senderCustomer.getBalance();
            BigDecimal currentBalanceRecipient = recipientCustomer.getBalance();

            float fees = transferDTO.getFees ();

            BigDecimal transferAmount = transferDTO.getTransferAmount ();
            BigDecimal feeAmount = transferAmount.multiply ( new BigDecimal ( fees ) ).divide ( new BigDecimal ( 100L ) );
            BigDecimal transactionAmount = transferAmount.add ( feeAmount );

            BigDecimal newBalanceSender = currentBalanceSender.subtract ( transactionAmount );

            BigDecimal newBalanceRecipient = currentBalanceRecipient.add ( transferAmount );

            transfer.setFeesAmount ( feeAmount );
            transfer.setTransactionAmount ( transactionAmount );
            Transfer transferred = transferService.save ( transfer );

            senderCustomer.setBalance ( newBalanceSender );
            customerService.save ( senderCustomer );

            recipientCustomer.setBalance ( newBalanceRecipient );
            customerService.save ( recipientCustomer );


            return new ResponseEntity<> ( transferred.toTransferDTO (), HttpStatus.OK );
        }
        return null;
    }
}
