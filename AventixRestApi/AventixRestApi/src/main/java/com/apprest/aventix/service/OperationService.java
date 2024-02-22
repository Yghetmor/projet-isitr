package com.apprest.aventix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.apprest.aventix.dao.CardDao;
import com.apprest.aventix.dao.OperationDao;
import org.springframework.stereotype.Service;

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
}
