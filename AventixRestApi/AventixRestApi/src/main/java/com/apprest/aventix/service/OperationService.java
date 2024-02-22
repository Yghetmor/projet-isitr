package com.apprest.aventix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.apprest.aventix.model.Card;
import com.apprest.aventix.model.Operation;
import com.apprest.aventix.dao.CardDao;
import com.apprest.aventix.dao.OperationDao;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class OperationService {
    private final CardDao cardDao;
    private final OperationDao operationDao;

    @Autowired
    public OperationService(CardDao cardDao, OperationDao operationDao) {
        this.cardDao = cardDao;
        this.operationDao = operationDao;
    }

    public boolean processOperation(Operation operation) {
        Optional<Card> optionCard = cardDao.findCardById(operation.getCardNo());
        if (optionCard.isEmpty()) {
            return false;
        } else {
            Card card = optionCard.get();
            if (card.getState() == Card.State.GOOD && card.getBalance().compareTo(operation.getAmount()) >= 0 && card.getDailyBalance().compareTo(operation.getAmount()) >= 0) {
                operation.setValid(true);
                card.setBalance(card.getBalance().subtract(operation.getAmount()));
                card.setDailyBalance(card.getDailyBalance().subtract(operation.getAmount()));
                cardDao.save(card);
                operationDao.save(operation);
                return true;
            } else {
                return false;
            }
        }
    }
}
