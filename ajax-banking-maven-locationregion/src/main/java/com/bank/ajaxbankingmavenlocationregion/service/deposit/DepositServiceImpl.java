package com.bank.ajaxbankingmavenlocationregion.service.deposit;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
import com.bank.ajaxbankingmavenlocationregion.repository.CustomerRepository;
import com.bank.ajaxbankingmavenlocationregion.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService{
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Deposit> findAll() {
        return null;
    }
    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty ();
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save ( deposit );
    }

    @Override
    public void remove(Long id) {
        depositRepository.deleteById ( id );
    }

    @Override
    public Customer doDeposit(Deposit deposit) {
        depositRepository.save ( deposit );

        Customer customer = deposit.getCustomer ();
        BigDecimal newBalance = customer.getBalance ().add ( deposit.getTransactionAmount () );

        customer.setBalance ( newBalance );

        customerRepository.incrementBalance ( customer.getId (), newBalance );
        return customer;
    }
}
