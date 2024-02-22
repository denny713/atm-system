package com.bank.service;

import com.bank.dto.ResponseDto;
import com.bank.dto.TransferDto;

public interface TransactionService {

    ResponseDto executeTransfer(TransferDto dto);
}
