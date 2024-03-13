package com.apprest.aventix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.apprest.aventix.model.Card;
import com.apprest.aventix.model.Operation;
import com.apprest.aventix.dao.CardDao;
import com.apprest.aventix.dao.OperationDao;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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

    public ResponseEntity<?> processOperation(Operation operation) {
        if (operation.getCardNo() == null || operation.getMerchantNo() == null || operation.getAmount() == null || operation.getDate() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Card> optionCard = cardDao.findCardById(operation.getCardNo());
        if (optionCard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Card card = optionCard.get();
            if (card.getState() == Card.State.GOOD && card.getBalance().compareTo(operation.getAmount()) >= 0 && card.getDailyBalance().compareTo(operation.getAmount()) >= 0) {
                operation.setValid(true);
                try {
                    card.setBalance(card.getBalance().subtract(operation.getAmount()));
                    card.setDailyBalance(card.getDailyBalance().subtract(operation.getAmount()));
                    cardDao.save(card);
                    operationDao.save(operation);
                }
                catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
    }
}
