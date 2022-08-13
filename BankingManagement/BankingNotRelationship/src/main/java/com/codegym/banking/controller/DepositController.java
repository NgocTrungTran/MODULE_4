package com.codegym.banking.controller;

import com.codegym.banking.Utils.Validator;
import com.codegym.banking.model.Customer;
import com.codegym.banking.model.Deposit;
import com.codegym.banking.service.customer.ICustomerService;
import com.codegym.banking.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/deposits")
public class DepositController {
    @Autowired
    private IDepositService depositService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/add/{id}")
    public ModelAndView showAddForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById ( id );
        ModelAndView modelAndView = new ModelAndView ( "/deposit/addDeposit" );
        if ( customer.isPresent () ) {
            modelAndView.addObject ( "customer", customer.get () );
            return modelAndView;
        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }

    @PostMapping("/add")
    public ModelAndView addDeposit(@ModelAttribute("customer") Customer customer, @RequestParam("transactionAmount") String transactionAmount) {
        ModelAndView modelAndView = new ModelAndView ( "/deposit/addDeposit" );
        if ( Validator.isIntValid ( transactionAmount ) ) {
            long transaction_amount = Long.parseLong ( transactionAmount );
            long id = System.currentTimeMillis () / 1000;
            Deposit deposit = new Deposit (id, LocalDateTime.now (), customer.getId (), transaction_amount );
            depositService.save ( deposit );
            Optional<Customer> customerAfterDeposit = customerService.findById ( customer.getId () );
            modelAndView.addObject ( "customer", customerAfterDeposit );
            modelAndView.addObject ( "message", "Deposit successfully" );
            return modelAndView;
        }
        else {
            modelAndView.addObject ( "error", "Transaction amount invalid!" );
            return modelAndView;
        }
    }
}
