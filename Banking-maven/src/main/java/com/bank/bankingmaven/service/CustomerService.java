package com.bank.bankingmaven.service;

import com.bank.bankingmaven.model.Customer;

import java.util.List;

public interface CustomerService extends IGeneralService<Customer>{
    List<Customer> findByIdIsNot (Long id);
    Iterable<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByDeletedIsFalseAndIdIsNot(Long id);
}
