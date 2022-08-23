package com.bank.ajaxbankingmavenlocationregion.model;

import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transfers")
@Accessors(chain = true)
public class Transfer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_amount", precision = 12, scale = 0)
    private BigDecimal transferAmount;

    private float fees;

    @Column(name = "fees_amount", precision = 12, scale = 0)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 0)
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Customer recipient;

    public TransferDTO toTransferDTO () {
        return new TransferDTO ()
                .setId ( id )
                .setFees ( fees )
                .setTransferAmount ( transferAmount )
                .setTransactionAmount ( transactionAmount )
                .setFeesAmount ( feesAmount )
                .setSenderId ( sender.getId () )
                .setRecipientId ( recipient.getId () );
    }
}
