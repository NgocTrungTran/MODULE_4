package com.codegym.banking.service;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

//    void updateCustomer(@Param("balance") Long balance, @Param("id") Long id);

    void remove(Long id);
}
