package com.codegym.banking.controller;

import com.codegym.banking.model.Customer;
import com.codegym.banking.model.Deposit;
import com.codegym.banking.model.Withdraw;
import com.codegym.banking.service.customer.ICustomerService;
import com.codegym.banking.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/withdraws")
public class WithdrawController {
    @Autowired
    private IWithdrawService withdrawService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/add/{id}")
    public ModelAndView showAddForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById ( id );
        ModelAndView modelAndView = new ModelAndView ( "/withdraw/withdraw" );
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
        ModelAndView modelAndView = new ModelAndView ( "/withdraw/withdraw" );
        long transaction_amount = Long.parseLong ( transactionAmount );
        long id = System.currentTimeMillis () / 1000;
        Withdraw withdraw = new Withdraw (id, LocalDateTime.now (), customer.getId (), transaction_amount );
        withdrawService.save ( withdraw );
        Optional<Customer> customerAfterWithdraw = customerService.findById ( customer.getId () );
        modelAndView.addObject ( "customer", customerAfterWithdraw );
        modelAndView.addObject ( "message", "Withdraw successfully" );
        return modelAndView;
    }
}
