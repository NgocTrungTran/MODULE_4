package com.bank.ajaxbankingmavenlocationregion.service.customer;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.dto.CustomerDTO;
import com.bank.ajaxbankingmavenlocationregion.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService extends IGeneralService<Customer> {
    List<Customer> findByIdIsNot (Long id);
    Iterable<Customer> findAllByDeletedIsFalse();

    List<CustomerDTO> findAllCustomerDTO();
    List<Customer> findAllByDeletedIsFalseAndIdIsNot(Long id);

    void incrementBalance(Long customerId, BigDecimal balance);
}
