package com.apprest.aventix.service;

import org.springframework.stereotype.Service;

import com.apprest.aventix.repository.CardDao;

@Service
public class OperationService {
    private final CardDao cardDao;

    public OperationService(CardDao cardDao) {
        this.cardDao = cardDao;
    }
}
