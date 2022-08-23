package com.bank.ajaxbankingmavenlocationregion.model.dto;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
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
public class TransferDTO {
    private Long id;

    private BigDecimal transferAmount;

    private float fees;

    private BigDecimal feesAmount;

    private BigDecimal transactionAmount;

    private Long senderId;

    private Long recipientId;

    public Transfer toTransfer(Customer sender, Customer recipient) {
        return new Transfer ()
                .setId ( id )
                .setTransferAmount ( transferAmount )
                .setFees ( fees )
                .setFeesAmount ( feesAmount )
                .setTransactionAmount ( transactionAmount )
                .setSender ( sender )
                .setRecipient ( recipient );
    }
}
