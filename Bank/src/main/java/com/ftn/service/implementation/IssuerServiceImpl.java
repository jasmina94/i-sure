package com.ftn.service.implementation;

import com.ftn.exception.BadRequestException;
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
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
    public PaymentResponseInfoDTO.CardAuthStatus checkCard(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.SUCCESSED;
        String pan = paymentOrderDTO.getPan();
        int securityCode = paymentOrderDTO.getSecurityCode();
        try{
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            if(card.getSecurityCode() == securityCode){
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.SUCCESSED;
            }else {
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
            }
        }catch (NotFoundException exception){
            cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
        }
        return cardAuthStatus;
    }

    @Override
    public PaymentResponseInfoDTO.TransactionStatus checkTransaction(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.TransactionStatus transactionStatus = PaymentResponseInfoDTO.TransactionStatus.SUCCESSED;
        String pan = paymentOrderDTO.getPan();
        try{
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            Account account = card.getAccount();
            if(paymentOrderDTO.getAmount() > account.getBalance()){
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.FAILED;
            }else {
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.SUCCESSED;
            }
        }catch (NotFoundException exception){
            transactionStatus = PaymentResponseInfoDTO.TransactionStatus.CARD_AUTH_FAILURE;
        }
        return transactionStatus;
    }

    @Override
    public Transaction reserve(PaymentOrderDTO paymentOrderDTO) {
        String PAN = paymentOrderDTO.getPan();
        double orderAmount = paymentOrderDTO.getAmount();
        Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
        Account account = card.getAccount();
        account.setReserved(account.getReserved()+orderAmount);
        account.setBalance(account.getBalance()-orderAmount);
        accountRepository.save(account);
        return transactionService.create(paymentOrderDTO, Transaction.TransactionType.CHARGE);
    }

    @Override
    public PaymentResponseInfoDTO makeResponse(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO paymentResponseInfoDTO = new PaymentResponseInfoDTO();
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus = checkCard(paymentOrderDTO);
        PaymentResponseInfoDTO.TransactionStatus transactionStatus = checkTransaction(paymentOrderDTO);

        if(cardAuthStatus.equals(PaymentResponseInfoDTO.CardAuthStatus.SUCCESSED)
                && transactionStatus.equals(PaymentResponseInfoDTO.TransactionStatus.SUCCESSED)){
            Transaction transaction = reserve(paymentOrderDTO);
            paymentResponseInfoDTO.setIssuerOrderId(transaction.getId());
            paymentResponseInfoDTO.setIssuerTimestamp(transaction.getTimestamp());
        }

        paymentResponseInfoDTO.setAcquirerOrderId(paymentOrderDTO.getAcquirerOrderId());
        paymentResponseInfoDTO.setAcquirerTimestamp(paymentOrderDTO.getAcquirerTimestamp());
        paymentResponseInfoDTO.setCardAuthStatus(cardAuthStatus);
        paymentResponseInfoDTO.setTransactionStatus(transactionStatus);
        return paymentResponseInfoDTO;
    }
}
