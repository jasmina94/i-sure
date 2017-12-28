package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Account;
import com.ftn.model.database.Card;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.AccountRepository;
import com.ftn.repository.CardRepository;
import com.ftn.service.IssuerService;
import com.ftn.service.OnlinePaymentService;
import com.ftn.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class IssuerServiceImpl implements IssuerService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OnlinePaymentService onlinePaymentService;

    @Autowired
    private EnvironmentProperties environmentProperties;


    @Override
    public PaymentResponseInfoDTO.CardAuthStatus cardAuthentication(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus;
        String pan = paymentOrderDTO.getPan();
        int securityCode = paymentOrderDTO.getSecurityCode();
        try {
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            if (card.getSecurityCode() == securityCode) {
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.SUCCESSFUL;
            } else {
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
            }
        } catch (NotFoundException exception) {
            cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
        }
        return cardAuthStatus;
    }

    @Override
    public PaymentResponseInfoDTO.TransactionStatus transactionAuthorization(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.TransactionStatus transactionStatus;
        String pan = paymentOrderDTO.getPan();
        try {
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            Account account = card.getAccount();
            if (paymentOrderDTO.getAmount() > account.getBalance()) {
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.FAILED;
            } else {
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.SUCCESSFUL;
            }
        } catch (NotFoundException exception) {
            transactionStatus = PaymentResponseInfoDTO.TransactionStatus.CARD_AUTH_FAILURE;
        }
        return transactionStatus;
    }

    @Override
    public Transaction transfer(PaymentOrderDTO paymentOrderDTO) {
        Transaction transaction;
        String PAN = paymentOrderDTO.getPan();
        double orderAmount = paymentOrderDTO.getAmount();
        Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
        try {
            Account account = card.getAccount();
            account.setBalance(account.getBalance() - orderAmount);
            accountRepository.save(account);
            transaction = transactionService.create(paymentOrderDTO, Transaction.TransactionType.CHARGE);
        } catch (NotFoundException exception) {
            transaction = null;
        }
        return transaction;
    }

    @Override
    public PaymentResponseInfoDTO generateResponse(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO paymentResponseInfoDTO = new PaymentResponseInfoDTO();
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus = cardAuthentication(paymentOrderDTO);
        PaymentResponseInfoDTO.TransactionStatus transactionStatus = transactionAuthorization(paymentOrderDTO);

        if (cardAuthStatus.equals(PaymentResponseInfoDTO.CardAuthStatus.SUCCESSFUL)
                && transactionStatus.equals(PaymentResponseInfoDTO.TransactionStatus.SUCCESSFUL)) {
            Transaction transaction = transfer(paymentOrderDTO);
            if (transaction != null) {
                paymentResponseInfoDTO.setIssuerOrderId(transaction.getId());
                paymentResponseInfoDTO.setIssuerTimestamp(transaction.getTimestamp());
            }
        }
        paymentResponseInfoDTO.setAcquirerOrderId(paymentOrderDTO.getAcquirerOrderId());
        paymentResponseInfoDTO.setAcquirerTimestamp(paymentOrderDTO.getAcquirerTimestamp());
        paymentResponseInfoDTO.setCardAuthStatus(cardAuthStatus);
        paymentResponseInfoDTO.setTransactionStatus(transactionStatus);
        return paymentResponseInfoDTO;
    }
}
