package com.apprest.aventix.repository;

import com.apprest.aventix.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CardDao extends JpaRepository<Card, Long> {
    Optional<Card> findCardById(Long id);
}
