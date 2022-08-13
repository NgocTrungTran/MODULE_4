package com.codegym.banking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "withdraws")
public class Withdraw {
    @Id
    private Long id;
    private LocalDateTime created_at;
    private Long created_by;
    private int deleted;
    private LocalDateTime updated_at;
    private Long updated_by;
    private Long customer_id;
    private Long transaction_amount;

    public Withdraw() {
    }

    public Withdraw(Long id, LocalDateTime created_at, Long customer_id, Long transaction_amount) {
        this.id = id;
        this.created_at = created_at;
        this.customer_id = customer_id;
        this.transaction_amount = transaction_amount;
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

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }
}
