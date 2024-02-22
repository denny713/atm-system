package com.bank.controller;

import com.bank.dto.TransferDto;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.impl.TransactionServiceImpl;
import com.bank.util.Crypt;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransactionServiceImpl transactionService;

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
    void givenTransferDto_whenExecuteTransfer_thenReturnOk() throws Exception {
        UUID id = UUID.randomUUID();
        transaction.setId(id);
        transaction.setActive(true);

        when(transactionService.executeTransfer(requestDto)).thenReturn(transaction);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(requestDto))
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        JsonObject jsonObject = JsonParser.parseString(result.getResponse().getContentAsString()).getAsJsonObject();
        assertEquals(id.toString(), jsonObject.get("id").getAsString());
    }
}
