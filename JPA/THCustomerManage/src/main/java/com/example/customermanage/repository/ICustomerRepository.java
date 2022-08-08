package com.example.customermanage.repository;

import com.example.customermanage.model.Customer;

public interface ICustomerRepository extends IGeneralRepository<Customer>{
    // su dung stored procedure
    boolean insertWithStoredProcedure(Customer customer);
}
