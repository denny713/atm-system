package com.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    @NotBlank(message = "From Account Cannot be Null ot Empty")
    @Size(min = 1, max = 6)
    private String fromAccountNo;
    @NotBlank(message = "To Account Cannot be Null ot Empty")
    @Size(min = 1, max = 6)
    private String toAccountNo;
    private BigDecimal amount;
}
