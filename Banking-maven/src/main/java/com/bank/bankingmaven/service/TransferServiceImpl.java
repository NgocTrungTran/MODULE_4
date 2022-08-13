package com.bank.bankingmaven.service;

import com.bank.bankingmaven.model.Customer;
import com.bank.bankingmaven.model.Transfer;
import com.bank.bankingmaven.model.dto.TransferHistoryDTO;
import com.bank.bankingmaven.repository.CustomerRepository;
import com.bank.bankingmaven.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TransferServiceImpl implements TransferService{
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Transfer> findAll() {
        return transferRepository.findAll ();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById ( id );
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save ( transfer );
    }

    @Override
    public void remove(Long id) {
        transferRepository.deleteById ( id );
    }

    @Override
    public List<TransferHistoryDTO> findAllHistories() {
        return transferRepository.findAllHistories ();
    }

    @Override
    public String doTransfer(Transfer transfer) {
        transferRepository.save(transfer);

        Customer sender = transfer.getSender();
        BigDecimal senderBalance = sender.getBalance().subtract(transfer.getTransactionAmount());
        sender.setBalance(senderBalance);
        customerRepository.save(sender);

        Customer recipient = transfer.getRecipient();
        BigDecimal recipientBalance = recipient.getBalance().add(transfer.getTransferAmount());
        if ( recipientBalance.toString ().length () > 12 ) {
            return "Lỗi hệ thống! Xin hãy liên hệ với người quản lý.";
        } else {
            recipient.setBalance(recipientBalance);
            customerRepository.save(recipient);
            return null;
        }
    }
}
