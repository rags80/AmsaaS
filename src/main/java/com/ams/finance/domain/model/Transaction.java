package com.ams.finance.domain.model;

import com.ams.users.domain.model.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.AccessType.PROPERTY;

@Entity
@Access(PROPERTY)
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Integer version;
    private Long transNumber;
    private float transAmount;
    private Date transDate;
    private String transType;
    private String transMode;
    private Account transAccount;
    private Person transPerson;

    public Transaction() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "Account_Number")
    public Account getTransAccount() {
        return transAccount;
    }

    public void setTransAccount(Account transAccount) {
        this.transAccount = transAccount;
    }

    public float getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(float transAmount) {
        this.transAmount = transAmount;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode;
    }

    @Id
    @GeneratedValue
    public Long getTransNumber() {
        return transNumber;
    }

    public void setTransNumber(Long transNumber) {
        this.transNumber = transNumber;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "Person_Id")
    public Person getTransPerson() {
        return transPerson;
    }

    public void setTransPerson(Person param) {
        this.transPerson = param;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
