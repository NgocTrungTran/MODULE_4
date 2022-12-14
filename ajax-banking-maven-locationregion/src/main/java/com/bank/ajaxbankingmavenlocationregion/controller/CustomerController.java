package com.bank.ajaxbankingmavenlocationregion.controller;


import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
import com.bank.ajaxbankingmavenlocationregion.model.Withdraw;
import com.bank.ajaxbankingmavenlocationregion.service.customer.CustomerService;
import com.bank.ajaxbankingmavenlocationregion.service.deposit.DepositService;
import com.bank.ajaxbankingmavenlocationregion.service.transfer.TransferService;
import com.bank.ajaxbankingmavenlocationregion.service.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @GetMapping
    public ModelAndView showListCustomer() {
        ModelAndView modelAndView = new ModelAndView ( "customer/list" );
        Iterable<Customer> customers = customerService.findAllByDeletedIsFalse ();
        modelAndView.addObject ( "customers", customers );
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ( "customer/create" );
        modelAndView.addObject ( "customer", new Customer () );

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView ( "customer/create" );
        customer.setBalance ( new BigDecimal ( 0L ) );
        customerService.save ( customer );
        modelAndView.addObject ( "customer", new Customer () );
        modelAndView.addObject ( "message", "Done" );
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView ( "customer/edit" );
        Optional<Customer> customer = customerService.findById ( id );
        if ( customer.isPresent () ) {
            modelAndView.addObject ( "customer", customer.get () );
            return modelAndView;
        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView ( "/customer/edit" );
        customerService.save ( customer );
        modelAndView.addObject ( "customer", customer );
        modelAndView.addObject ( "message", "Customer updated successfully" );
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById ( id );
        System.out.println ( customer );
        ModelAndView modelAndView = new ModelAndView ( "/customer/remove" );
        if ( customer.isPresent () ) {
            modelAndView.addObject ( "customer", customer.get () );
            return modelAndView;

        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }

    @PostMapping("/remove")
    public ModelAndView removeCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView ( "/customer/list" );
        customer.setDeleted ( true );
        customerService.save ( customer );
        Iterable<Customer> customers = customerService.findAllByDeletedIsFalse ();
        modelAndView.addObject ( "customers", customers );
        modelAndView.addObject ( "message", "Remove successfully" );
        return modelAndView;
    }

    @GetMapping("/deposit/{id}")
    public ModelAndView showFormDeposit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView ( "customer/deposit" );
        Optional<Customer> customer = customerService.findById ( id );
        if ( !customer.isPresent () ) {
            modelAndView.setViewName ( "error-404" );
            return modelAndView;
        }
        modelAndView.addObject ( "customer", customer.get () );
        modelAndView.addObject ( "deposit", new Deposit () );
        return modelAndView;
    }

    @PostMapping("/deposit/{id}")
    public ModelAndView depositAction(@PathVariable("id") Long id, @ModelAttribute Deposit deposit) {
        ModelAndView modelAndView = new ModelAndView ( "customer/deposit" );
        Optional<Customer> customerOptional = customerService.findById ( id );

        Customer customer = customerOptional.get ();
        BigDecimal currentBalance = customer.getBalance ();
        BigDecimal transactionAmount = deposit.getTransactionAmount ();
        BigDecimal newBalance = currentBalance.add ( transactionAmount );

        deposit.setCustomer ( customer );
        customer.setBalance ( newBalance );


        depositService.save ( deposit );
        Customer newCustomer = customerService.save ( customer );
        modelAndView.addObject ( "customer", newCustomer );
        modelAndView.addObject ( "deposit", new Deposit () );
        modelAndView.addObject ( "message", "Deposit successfully" );

        return modelAndView;
    }

    @GetMapping("/withdraw/{id}")
    public ModelAndView showFormWithdraw(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView ( "customer/withdraw" );
        Optional<Customer> customer = customerService.findById ( id );
        if ( !customer.isPresent () ) {
            modelAndView.setViewName ( "error-404" );
            return modelAndView;
        }
        modelAndView.addObject ( "customer", customer.get () );
        modelAndView.addObject ( "withdraw", new Withdraw () );
        return modelAndView;
    }

    @PostMapping("/withdraw/{id}")
    public ModelAndView withdrawAction(@PathVariable("id") Long id, @ModelAttribute Withdraw withdraw) {
        ModelAndView modelAndView = new ModelAndView ( "customer/withdraw" );
        Optional<Customer> customerOptional = customerService.findById ( id );

        Customer customer = customerOptional.get ();
        BigDecimal currentBalance = customer.getBalance ();
        BigDecimal transactionAmount = withdraw.getTransactionAmount ();
        BigDecimal newBalance = currentBalance.subtract ( transactionAmount );
        if ( transactionAmount.compareTo ( currentBalance ) > 0 ) {
            modelAndView.addObject ( "error", "S??? d?? t??i kho???n kh??ng ????? th???c hi???n giao d???ch" );
            Customer newCustomer = customerService.save ( customer );
            modelAndView.addObject ( "customer", newCustomer );
            modelAndView.addObject ( "withdraw", new Withdraw () );
            return modelAndView;
        } else {
            withdraw.setCustomer ( customer );
            customer.setBalance ( newBalance );
            withdrawService.save ( withdraw );
            Customer newCustomer = customerService.save ( customer );
            modelAndView.addObject ( "customer", newCustomer );
            modelAndView.addObject ( "withdraw", new Withdraw () );
            modelAndView.addObject ( "message", "Withdraw successfully" );
            return modelAndView;
        }
    }

    @GetMapping("/transfer/{senderId}")
    public ModelAndView showFormTransfer(@PathVariable("senderId") Long senderId, @ModelAttribute Customer recipient) {
        ModelAndView modelAndView = new ModelAndView ( "customer/transfer" );
        Optional<Customer> sender = customerService.findById ( senderId );
        if ( !sender.isPresent () ) {
            modelAndView.setViewName ( "error-404" );
            return modelAndView;
        }
        Iterable<Customer> recipients = customerService.findAllByDeletedIsFalseAndIdIsNot ( senderId );

        modelAndView.addObject ( "sender", sender.get () );
        modelAndView.addObject ( "recipients", recipients );
        modelAndView.addObject ( "transfer", new Transfer () );
        return modelAndView;
    }

    @PostMapping("/transfer/{senderId}")
    public ModelAndView doTransfer(@PathVariable Long senderId, @ModelAttribute Transfer transfer) {
        ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ( "customer/transfer" );

        Optional<Customer> senderOptional = customerService.findById ( senderId );
        Customer sender = senderOptional.get ();

        BigDecimal currentSenderBalance = sender.getBalance ();

        BigDecimal transferAmount = transfer.getTransferAmount ();
        BigDecimal fees = new BigDecimal ( 10L );
        BigDecimal feesAmount = transferAmount.multiply ( fees ).divide ( new BigDecimal ( 100L ) );
        BigDecimal transactionAmount = transferAmount.add ( feesAmount );

        if ( transactionAmount.compareTo ( currentSenderBalance ) > 0 ) {
            modelAndView.addObject ( "error", "S??? d?? t??i kho???n kh??ng ????? th???c hi???n giao d???ch" );
        } else {
            transfer.setFees ( fees.longValueExact () );
            transfer.setFeesAmount ( feesAmount );
            transfer.setTransactionAmount ( transactionAmount );
            transfer.setSender ( senderOptional.get () );
            transfer.setSender ( sender );

//            transferService.save ( transfer );
//
//            currentSenderBalance = currentSenderBalance.subtract ( transactionAmount );
//            sender.setBalance ( currentSenderBalance );
//
//            sender = customerService.save ( sender );
//
//            Customer recipient = transfer.getRecipient ();
//            BigDecimal newRecipientBalance = recipient.getBalance ();
//            newRecipientBalance = newRecipientBalance.add ( transferAmount );
//            recipient.setBalance ( newRecipientBalance );
//
//            customerService.save ( recipient );

            String err = transferService.doTransfer(transfer);
            if ( err == null ) {
                modelAndView.addObject ( "message", "Chuy???n ti???n th??nh c??ng" );
            } else {
                modelAndView.addObject ( "error", err );
            }
        }

        Iterable<Customer> recipients = customerService.findAllByDeletedIsFalseAndIdIsNot ( senderId );

        modelAndView.addObject ( "sender", sender );
        modelAndView.addObject ( "recipients", recipients );
        modelAndView.addObject ( "transfer", new Transfer () );

        return modelAndView;
    }
}
