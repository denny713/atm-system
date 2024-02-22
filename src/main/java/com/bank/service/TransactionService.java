package com.bank.service;

import com.bank.dto.ResponseDto;
import com.bank.dto.TransferDto;
import com.bank.model.Transaction;

public interface TransactionService {

    Transaction executeTransfer(TransferDto dto);
}
