package com.bank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @Column(name = "account_number", length = 6)
    private String accountNumber;

    @Column(name = "activity", length = 20)
    private String activity;

    @Column(name = "amount")
    private BigDecimal amount;
}
