package com.codegym.banking.controller;

import com.codegym.banking.Utils.Validator;
import com.codegym.banking.model.Customer;
import com.codegym.banking.model.Deposit;
import com.codegym.banking.model.Transfer;
import com.codegym.banking.service.customer.ICustomerService;
import com.codegym.banking.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private ITransferService transferService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/add/{id}")
    public ModelAndView showAddForm(@PathVariable Long id) {
        Optional<Customer> Sender = customerService.findById ( id );
        ModelAndView modelAndView = new ModelAndView ( "/transfer/transfer" );
        if ( Sender.isPresent () ) {
            modelAndView.addObject ( "sender", Sender.get () );
            return modelAndView;
        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }
    @PostMapping("/add")
    public ModelAndView addDeposit(@ModelAttribute("sender") Customer sender,
                                   @RequestParam("recipient_id") String recipient_id,
                                   @RequestParam("transferAmount") String transferAmount) {
        ModelAndView modelAndView = new ModelAndView ( "/transfer/transfer" );
        if ( Validator.isIntValid ( recipient_id ) ){
            Long recipientId = Long.valueOf ( recipient_id );
            if ( Validator.isIntValid ( transferAmount ) ) {
                Optional<Customer> recipient = customerService.findById ( recipientId );
                long transfer_amount = Long.parseLong ( transferAmount );
                long id = System.currentTimeMillis () / 1000;
                Transfer transfer = new Transfer ();
                transferService.save ( transfer );
                Optional<Customer> customerAfterTransfer = customerService.findById ( sender.getId () );
                modelAndView.addObject ( "customer", customerAfterTransfer );
                modelAndView.addObject ( "message", "Deposit successfully" );
                return modelAndView;
            }
            else {
                modelAndView.addObject ( "error", "Transfer amount invalid!" );
                return modelAndView;
            }
        }else {
            modelAndView.addObject ( "error", "Recipient not exists!" );
            return modelAndView;
        }
    }
}
