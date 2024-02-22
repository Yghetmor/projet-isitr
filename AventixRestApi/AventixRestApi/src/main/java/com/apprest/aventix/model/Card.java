package com.apprest.aventix.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Card implements Serializable {

    public enum State {
        GOOD,
        BAD
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private BigDecimal balance;
    private BigDecimal dailyBalance;
    private State state;

    public Card() {
    }

    public Card(BigDecimal balance, BigDecimal dailyBalance, State state) {
        this.balance = balance;
        this.dailyBalance = dailyBalance;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getDailyBalance() {
        return dailyBalance;
    }

    public State getState() {
        return state;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setDailyBalance(BigDecimal dailyBalance) {
        this.dailyBalance = dailyBalance;
    }

    public void setState(State state) {
        this.state = state;
    }
}
