package com.codegym.banking.model;

import java.time.LocalDateTime;

public class Transfer {
    private long id;
    private LocalDateTime created_at;
    private long created_by;
    private int deleted;
    private LocalDateTime updated_at;
    private long updated_by;
    private int fees;
    private long fees_amount;
    private long transactions_amount;
    private long transfer_amount;
    private long recipient_id;
    private long sender_id;

    public Transfer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public long getFees_amount() {
        return fees_amount;
    }

    public void setFees_amount(long fees_amount) {
        this.fees_amount = fees_amount;
    }

    public long getTransactions_amount() {
        return transactions_amount;
    }

    public void setTransactions_amount(long transactions_amount) {
        this.transactions_amount = transactions_amount;
    }

    public long getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(long transfer_amount) {
        this.transfer_amount = transfer_amount;
    }

    public long getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(long recipient_id) {
        this.recipient_id = recipient_id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }
}
