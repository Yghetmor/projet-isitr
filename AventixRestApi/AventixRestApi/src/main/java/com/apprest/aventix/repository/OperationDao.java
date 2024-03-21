package com.apprest.aventix.repository;

import com.apprest.aventix.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation, Long> {
}
