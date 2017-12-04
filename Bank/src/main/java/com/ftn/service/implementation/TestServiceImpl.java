package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Card;
import com.ftn.repository.CardRepository;
import com.ftn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class TestServiceImpl implements TestService {

    private final CardRepository cardRepository;

    @Autowired
    public TestServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> read() {
        return cardRepository.findAll();
    }

    @Override
    public Card create(Card card) {
        card = cardRepository.save(card);
        return card;
    }

    @Override
    public Card update(Long id, Card card) {
        Card existingCard = cardRepository.findOne(id);
        if(existingCard != null){
            existingCard.setAccount(card.getAccount());
            existingCard.setCardHolderName(card.getCardHolderName());
            existingCard.setCardType(card.getCardType());
            existingCard.setExpirationMonth(card.getExpirationMonth());
            existingCard.setExpirationYear(card.getExpirationYear());
            existingCard.setPan(card.getPan());
            existingCard.setPin(card.getPin());
            existingCard.setSecurityCode(card.getSecurityCode());
        }else {
            throw new NotFoundException();
        }
        return cardRepository.save(existingCard);
    }

    @Override
    public void delete(Long id) {
        Card existingCard = cardRepository.findOne(id);
        if(existingCard == null){
            throw new NotFoundException();
        }else {
            cardRepository.delete(id);
        }
        return;
    }
}
