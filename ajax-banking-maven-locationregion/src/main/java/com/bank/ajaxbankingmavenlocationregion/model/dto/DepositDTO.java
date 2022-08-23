package com.bank.ajaxbankingmavenlocationregion.model.dto;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
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
public class DepositDTO {
    private Long id;
    private Long customerId;
    private BigDecimal transactionAmount;

    public Deposit toDeposit(Customer customer) {
        return new Deposit ()
                .setId ( id )
                .setCustomer ( customer )
                .setTransactionAmount ( transactionAmount );
    }
}
