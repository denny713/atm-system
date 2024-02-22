package com.bank.controller;

import com.bank.dto.ResponseDto;
import com.bank.dto.TransferDto;
import com.bank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transfer")
public class TransferController {

    private final TransactionService transactionService;

    public TransferController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> transfer(@Valid @RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(transactionService.executeTransfer(transferDto));
    }
}
