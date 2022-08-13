package com.codegym.banking.service.customer;

import com.codegym.banking.model.Customer;
import com.codegym.banking.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICustomerService extends IGeneralService<Customer> {
//    Page<Customer> findAll(Pageable pageable);
//    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
//    void createCustomer(@Param("full_name") String full_name, @Param("email") String email, @Param("phone") String phone, @Param("address") String address);
    void updateCustomer(@Param("balance") Long balance, @Param("id") Long id);
}
