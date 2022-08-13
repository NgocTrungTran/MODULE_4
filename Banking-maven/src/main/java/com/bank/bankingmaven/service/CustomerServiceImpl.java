package com.bank.bankingmaven.service;

import com.bank.bankingmaven.model.Customer;
import com.bank.bankingmaven.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll ();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById ( id );
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save ( customer );
    }

    @Override
    public List<Customer> findByIdIsNot(Long id) {
        return customerRepository.findByIdIsNot ( id );
    }

    @Override
    public Iterable<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse ();
    }

    @Override
    public List<Customer> findAllByDeletedIsFalseAndIdIsNot(Long id) {
        return customerRepository.findAllByDeletedIsFalseAndIdIsNot ( id );
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById ( id );
    }
}
