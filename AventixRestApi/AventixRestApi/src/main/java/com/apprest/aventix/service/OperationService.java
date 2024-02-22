package com.apprest.aventix.service;

import com.apprest.aventix.dao.CardDao;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    private final CardDao cardDao;

    public OperationService(CardDao cardDao) {
        this.cardDao = cardDao;
    }
}
