package com.ftn.service;

import com.ftn.model.database.Transaction;
import com.ftn.model.dto.transaction.TransactionDTO;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
public interface TransactionService {

    List<Transaction> read();

    Transaction create(Transaction transaction);

    Transaction update(Long id, Transaction transaction);

    void delete(Long id);
}
