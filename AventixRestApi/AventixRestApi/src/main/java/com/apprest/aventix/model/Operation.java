package com.apprest.aventix.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Long cardNo;
    private Long merchantNo;
    private BigDecimal amount;
    private Date date;
    private boolean isValid;

    //Constructors
    public Operation() {
    }

    public Operation(Long cardNo, Long merchantNo, BigDecimal amount, Date date, boolean isValid) {
        this.cardNo = cardNo;
        this.merchantNo = merchantNo;
        this.amount = amount;
        this.date = date;
        this.isValid = isValid;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public Long getCardNo() {
        return cardNo;
    }

    public Long getMerchantNo() {
        return merchantNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public void setMerchantNo(Long merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
