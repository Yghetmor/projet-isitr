package com.apprest.aventix.service;

import com.apprest.aventix.dao.CardDao;
import com.apprest.aventix.dao.OperationDao;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    private final CardDao cardDao;
    private final OperationDao operationDao;

    public OperationService(CardDao cardDao, OperationDao operationDao) {
        this.cardDao = cardDao;
        this.operationDao = operationDao;
    }
}
