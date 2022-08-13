package com.codegym.banking.controller;

import com.codegym.banking.model.Customer;
import com.codegym.banking.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/list")
    public ModelAndView listCustomers(){
        Iterable<Customer> customers = customerService.findAll ();
        ModelAndView modelAndView = new ModelAndView ("customer/listCustomers");
        modelAndView.addObject ( "customers", customers );
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView ( "/customer/createCustomer" );
        modelAndView.addObject ( "customer", new Customer () );
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customer.setCreated_at ( LocalDateTime.now () );
        customerService.save ( customer );
        ModelAndView modelAndView = new ModelAndView ( "/customer/createCustomer" );
        modelAndView.addObject ( "customer", new Customer () );
        modelAndView.addObject ( "message", "New customer created successfully");
        return modelAndView;
    }
//    @PostMapping("/create")
//    public ModelAndView saveCustomer(@RequestParam("full_name") String full_name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("address") String address) {
//        customerService.createCustomer ( full_name, email, phone, address );
//        ModelAndView modelAndView = new ModelAndView ( "/customer/createCustomer" );
//        modelAndView.addObject ( "customer", new Customer () );
//        modelAndView.addObject ( "message", "New customer created successfully");
//        return modelAndView;
//    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById ( id );
        ModelAndView modelAndView = new ModelAndView ( "/customer/updateCustomer" );
        if ( customer.isPresent () ) {
            modelAndView.addObject ( "customer", customer.get () );
            return modelAndView;

        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }
    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customer.setUpdated_at ( LocalDateTime.now () );
        customerService.save ( customer );
        ModelAndView modelAndView = new ModelAndView ( "/customer/updateCustomer" );
        modelAndView.addObject ( "customer", customer );
        modelAndView.addObject ( "message", "Customer updated successfully" );
        return modelAndView;
    }

    @GetMapping("/suspended/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById ( id );
        System.out.println ( customer );
        ModelAndView modelAndView = new ModelAndView ( "/customer/suspension" );
        if ( customer.isPresent () ) {
            modelAndView.addObject ( "customer", customer.get () );
            return modelAndView;

        } else {
            modelAndView.addObject ( "error", "Customer not exists!" );
            return modelAndView;
        }
    }
    @PostMapping("/suspended")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove ( customer.getId () );
        Iterable<Customer> customers = customerService.findAll ();
        ModelAndView modelAndView = new ModelAndView ("customer/listCustomers");
        modelAndView.addObject ( "customers", customers );
        modelAndView.addObject ( "message", "Suspended completed" );
        return modelAndView;
    }
}
