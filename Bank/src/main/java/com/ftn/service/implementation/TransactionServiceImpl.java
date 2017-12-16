package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Card;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.CardService;
import com.ftn.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;

    @Override
    public List<Transaction> read() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction create(PaymentOrderDTO paymentOrderDTO) {
        Transaction transaction = new Transaction();
        transaction.setStatus(Transaction.Status.PENDING);
        transaction.setType(Transaction.TransactionType.CHARGE);
        transaction.setAmount(paymentOrderDTO.getAmount());

        Card card = cardService.findCard(paymentOrderDTO);
        transaction.setAccount(card.getAccount());

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        Transaction existing = transactionRepository.findOne(id);
        if(existing != null){
            existing = transaction;
            transactionRepository.save(existing);
        }else{
            throw new NotFoundException();
        }
        return existing;
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(id);
    }
}
