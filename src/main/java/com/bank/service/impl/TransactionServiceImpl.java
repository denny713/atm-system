package com.bank.service.impl;

import com.bank.dto.ResponseDto;
import com.bank.dto.TransferDto;
import com.bank.exception.BadRequestException;
import com.bank.exception.NotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction executeTransfer(TransferDto dto) {
        Optional<Account> fromAccount = accountRepository.findByAccountNumber(dto.getFromAccountNo());
        if (fromAccount.isEmpty()) {
            throw new NotFoundException("From Account number Not Found");
        }

        Optional<Account> toAccount = accountRepository.findByAccountNumber(dto.getToAccountNo());
        if (toAccount.isEmpty()) {
            throw new NotFoundException("To Account number Not Found");
        }

        if (fromAccount.get().getBalance().compareTo(dto.getAmount()) < 0) {
            throw new BadRequestException("Balance Not Enough to Transfer");
        }

        Account from = fromAccount.get();
        Account to = toAccount.get();
        Transaction trans = new Transaction();

        from.setBalance(from.getBalance().subtract(dto.getAmount()));
        to.setBalance(to.getBalance().add(dto.getAmount()));
        trans.setAccount(from);
        trans.setActivity("Transfer");
        trans.setAmount(dto.getAmount());

        accountRepository.save(from);
        accountRepository.save(to);
        return transactionRepository.save(trans);
    }
}
