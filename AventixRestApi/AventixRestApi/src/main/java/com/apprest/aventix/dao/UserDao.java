package com.apprest.aventix.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apprest.aventix.model.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
