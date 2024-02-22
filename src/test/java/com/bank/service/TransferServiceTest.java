package com.bank.service;

import com.bank.dto.TransferDto;
import com.bank.exception.NotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.impl.TransactionServiceImpl;
import com.bank.util.Crypt;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    private Account fromAccount = new Account();
    private Account toAccount = new Account();
    private Transaction transaction = new Transaction();
    private TransferDto requestDto = new TransferDto();

    @BeforeEach
    void init() throws NoSuchAlgorithmException {

        fromAccount.setId(UUID.randomUUID());
        fromAccount.setAccountNumber("112233");
        fromAccount.setPin(Crypt.encryptText("123465"));
        fromAccount.setBalance(new BigDecimal("3000000"));

        toAccount.setId(UUID.randomUUID());
        toAccount.setAccountNumber("445566");
        toAccount.setPin(Crypt.encryptText("789465"));
        toAccount.setBalance(new BigDecimal("1000000"));

        transaction.setAccount(fromAccount);
        transaction.setActivity("Transfer");
        transaction.setAmount(new BigDecimal("1000000"));

        requestDto.setFromAccountNo("112233");
        requestDto.setToAccountNo("445566");
        requestDto.setAmount(new BigDecimal("1000000"));
    }

    @Test
    void givenTransferDto_whenExecuteTransfer_thenReturnOk() {
        when(accountRepository.save(fromAccount)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(accountRepository.save(toAccount)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(transactionRepository.save(transaction)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Transaction transSave = transactionService.executeTransfer(requestDto);

        assertEquals(transaction.getActivity(), transSave.getActivity());
        assertEquals(transaction.getAmount(), transSave.getAmount());
    }

    @Test
    void givenTransferDto_whenExecuteTransfer_thenThrowFromAccountNotFound() {
        when(accountRepository.findByAccountNumber(requestDto.getFromAccountNo())).thenReturn(Optional.empty());
        when(accountRepository.findByAccountNumber(requestDto.getToAccountNo())).thenReturn(Optional.of(toAccount));

        try {
            transactionService.executeTransfer(requestDto);
        } catch (NotFoundException e) {
            assertTrue(ObjectUtils.isNotEmpty(e.getMessage()));
            assertEquals("From Account number Not Found", e.getMessage());
        }
    }

    @Test
    void givenTransferDto_whenExecuteTransfer_thenThrowToAccountNotFound() {
        when(accountRepository.findByAccountNumber(requestDto.getFromAccountNo())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(requestDto.getToAccountNo())).thenReturn(Optional.empty());

        try {
            transactionService.executeTransfer(requestDto);
        } catch (NotFoundException e) {
            assertTrue(ObjectUtils.isNotEmpty(e.getMessage()));
            assertEquals("To Account number Not Found", e.getMessage());
        }
    }

    @Test
    void givenTransferDto_whenExecuteTransfer_thenThrowNotEnoughException() {
        fromAccount.setBalance(new BigDecimal("0"));
        when(accountRepository.findByAccountNumber(requestDto.getFromAccountNo())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(requestDto.getToAccountNo())).thenReturn(Optional.of(toAccount));

        try {
            transactionService.executeTransfer(requestDto);
        } catch (NotFoundException e) {
            assertTrue(ObjectUtils.isNotEmpty(e.getMessage()));
            assertEquals("Balance Not Enough to Transfer", e.getMessage());
        }
    }
}
