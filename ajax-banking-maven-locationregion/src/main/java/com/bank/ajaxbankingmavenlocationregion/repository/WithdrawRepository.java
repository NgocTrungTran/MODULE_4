package com.bank.ajaxbankingmavenlocationregion.repository;

import com.bank.ajaxbankingmavenlocationregion.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

}
