package com.bank.ajaxbankingmavenlocationregion.service.transfer;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferHistoryDTO;
import com.bank.ajaxbankingmavenlocationregion.repository.CustomerRepository;
import com.bank.ajaxbankingmavenlocationregion.repository.TransferRepository;
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
            return "L???i h??? th???ng! Xin h??y li??n h??? v???i ng?????i qu???n l??.";
        } else {
            recipient.setBalance(recipientBalance);
            customerRepository.save(recipient);
            return null;
        }
    }
}
