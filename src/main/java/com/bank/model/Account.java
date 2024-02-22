package com.bank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "pin", length = 64)
    private String pin;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "account_number", length = 6)
    private String accountNumber;
}
