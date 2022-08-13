package com.codegym.banking.service.deposit;

import com.codegym.banking.model.Deposit;
import com.codegym.banking.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DepositService implements IDepositService{
    @Autowired
    private IDepositRepository depositRepository;
    @Override
    public Iterable<Deposit> findAll() {
        return depositRepository.findAll ();
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById ( id );
    }

    @Override
    public void save(Deposit deposit) {
        depositRepository.save ( deposit );
    }


    @Override
    public void remove(Long id) {
        depositRepository.deleteById ( id );
    }
}
