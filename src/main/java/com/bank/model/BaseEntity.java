package com.bank.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_active")
    protected boolean active;

    @Column(name = "created_by", length = 50)
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_date")
    protected LocalDateTime createdDate;

    @Column(name = "updated_by", length = 50)
    protected String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        this.active = true;
        this.setCreatedBy("System");
        this.setCreatedDate(LocalDateTime.now());
        this.setUpdatedBy("System");
        this.setUpdatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy("System");
        this.setUpdatedDate(LocalDateTime.now());
    }
}
