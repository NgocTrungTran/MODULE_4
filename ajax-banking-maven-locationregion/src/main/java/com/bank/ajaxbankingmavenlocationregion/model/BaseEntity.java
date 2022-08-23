package com.bank.ajaxbankingmavenlocationregion.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

}
