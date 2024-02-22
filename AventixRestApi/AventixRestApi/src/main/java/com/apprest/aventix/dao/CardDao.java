package com.apprest.aventix.dao;

import com.apprest.aventix.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDao extends JpaRepository<Card, Long> {
}
