package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, UUID> {
}
