package com.bank.bankingmaven.service;

import com.bank.bankingmaven.model.Withdraw;
import com.bank.bankingmaven.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WithdrawServiceImpl implements WithdrawService{
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll ();
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return withdrawRepository.findById ( id );
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return withdrawRepository.save ( withdraw );
    }

    @Override
    public void remove(Long id) {
        withdrawRepository.deleteById ( id );
    }
}
