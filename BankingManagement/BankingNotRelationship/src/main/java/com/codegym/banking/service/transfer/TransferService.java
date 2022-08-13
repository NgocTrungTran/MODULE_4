package com.codegym.banking.service.transfer;

import com.codegym.banking.model.Transfer;
import com.codegym.banking.repository.ICustomerRepository;
import com.codegym.banking.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService implements ITransferService{
    @Autowired
    private ITransferRepository transferRepository;

    @Override
    public Iterable<Transfer> findAll() {
        return transferRepository.findAll ();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById ( id );
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save ( transfer );
    }

    @Override
    public void remove(Long id) {
        transferRepository.deleteById ( id );
    }
}
