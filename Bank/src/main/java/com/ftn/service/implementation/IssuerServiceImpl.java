package com.ftn.service.implementation;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Account;
import com.ftn.model.database.Card;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.repository.AccountRepository;
import com.ftn.repository.CardRepository;
import com.ftn.service.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class IssuerServiceImpl implements IssuerService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean checkCustomer(String PAN) {
        boolean ok = true;
        try{
            Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
        }catch (NotFoundException exception){
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean checkCustomerAndAmount(PaymentOrderDTO paymentOrderDTO) {
        boolean ok = true;
        String PAN = paymentOrderDTO.getPAN();
        try{
            Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
            Account account = card.getAccount();
            double balance = account.getBalance();
            if(paymentOrderDTO.getAmount() > balance){
                throw new BadRequestException();
            }
        }catch (NotFoundException exception){
            ok = false;
        }catch (BadRequestException exception){
            ok = false;
        }
        return ok;
    }

    @Override
    public PaymentResponseInfoDTO reserveAndResponse(PaymentOrderDTO paymentOrderDTO) {
        String PAN = paymentOrderDTO.getPAN();
        double orderAmount = paymentOrderDTO.getAmount();
        Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
        Account account = card.getAccount();
        account.setReserved(account.getReserved()+orderAmount);
        account.setBalance(account.getBalance()-orderAmount);
        accountRepository.save(account);

        PaymentResponseInfoDTO paymentResponseInfo = new PaymentResponseInfoDTO();
        paymentResponseInfo.setAcquirerOrderId(paymentOrderDTO.getAcquirerOrderId());
        paymentResponseInfo.setAcquirerTimestamp(paymentOrderDTO.getAcquirerTimestamp());
        paymentResponseInfo.setIssuerOrderId(1);
        paymentResponseInfo.setIssuerTimestamp(new Date());

        return paymentResponseInfo;
    }
}
