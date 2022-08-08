package com.example.customermanage.service;

import com.example.customermanage.model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {
    // su dung stored procedure
    boolean insertWithStoredProcedure(Customer customer);
}
