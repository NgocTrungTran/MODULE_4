package com.bank.bankingmaven.repository;

import com.bank.bankingmaven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByIdIsNot (Long id);
    Iterable<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByDeletedIsFalseAndIdIsNot(Long id);
}
