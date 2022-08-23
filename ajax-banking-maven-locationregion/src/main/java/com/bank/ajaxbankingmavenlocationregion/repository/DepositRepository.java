package com.bank.ajaxbankingmavenlocationregion.repository;

import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
