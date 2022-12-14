package com.bank.bankingmaven.model.dto;

import java.math.BigDecimal;

public class TransferHistoryDTO {
    private Long id;
    private Long senderId;
    private String senderName;
    private Long recipientId;
    private String recipientName;
    private BigDecimal transferAmount;
    private float fees;
    private BigDecimal feesAmount;

    public TransferHistoryDTO(Long id, Long senderId, String senderName, Long recipientId, String recipientName, BigDecimal transferAmount, float fees, BigDecimal feesAmount) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
    }

    public TransferHistoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }
}
