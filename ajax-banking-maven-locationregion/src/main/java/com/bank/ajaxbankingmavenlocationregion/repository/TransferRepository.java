package com.bank.ajaxbankingmavenlocationregion.repository;

import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Query("SELECT NEW com.bank.ajaxbankingmavenlocationregion.model.dto.TransferHistoryDTO (" +
            "t.id, " +
            "t.sender.id, " +
            "t.sender.fullName, " +
            "t.recipient.id, " +
            "t.recipient.fullName, " +
            "t.transferAmount, " +
            "t.fees, " +
            "t.feesAmount " +
            ") " +
            "FROM Transfer AS t"
    )
    List<TransferHistoryDTO> findAllHistories();
}
