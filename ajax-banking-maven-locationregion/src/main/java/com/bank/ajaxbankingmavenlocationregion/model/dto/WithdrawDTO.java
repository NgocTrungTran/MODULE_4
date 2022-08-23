package com.bank.ajaxbankingmavenlocationregion.model.dto;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
import com.bank.ajaxbankingmavenlocationregion.model.Withdraw;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class WithdrawDTO {
    private Long id;
    private Long customerId;
    private BigDecimal transactionAmount;

    public Withdraw toDeposit(Customer customer) {
        return new Withdraw ()
                .setId ( id )
                .setCustomer ( customer )
                .setTransactionAmount ( transactionAmount );
    }
}
