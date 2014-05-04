package com.ams.finance.domain.model;

import com.ams.users.domain.model.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static javax.persistence.AccessType.PROPERTY;

@Entity
@Access(PROPERTY)
@Table(name = "T_ACCOUNT")
public class Account implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Version
    protected Integer version;
    private Long acntNumber;
    private String acntName;
    private String acntType;
    private String acntCurrency;
    private float acntBalance;
    private AccountDetail acntDetail;
    private Date acntCreationDate;
    private Set<Transaction> acntTransactions;
    private Person acntHolder;

    public Account() {
    }

    public void addTransaction(Transaction tran) {
        if (tran.getTransType().equalsIgnoreCase("credit")) {
            this.creditAmount(tran.getTransAmount());
        } else if (tran.getTransType().equalsIgnoreCase("debit")) {
            this.debitAmount(tran.getTransAmount());
        }

        this.getAcntTransactions().add(tran);
    }

    private void creditAmount(float amount) {
        this.acntBalance += amount;
    }

    private void debitAmount(float amount) {
        this.acntBalance -= amount;
    }

    public float getAcntBalance() {
        return acntBalance;
    }

    public void setAcntBalance(float balance) {
        this.acntBalance = balance;
    }

    public Date getAcntCreationDate() {
        return acntCreationDate;
    }

    public void setAcntCreationDate(Date acntCreationDate) {
        this.acntCreationDate = acntCreationDate;
    }

    public String getAcntCurrency() {
        return acntCurrency;
    }

    public void setAcntCurrency(String acntCurrency) {
        this.acntCurrency = acntCurrency;
    }

    @Embedded
    public AccountDetail getAcntDetail() {
        return acntDetail;
    }

    public void setAcntDetail(AccountDetail detail) {
        this.acntDetail = detail;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "Person_Id")
    public Person getAcntHolder() {
        return acntHolder;
    }

    public void setAcntHolder(Person person) {
        this.acntHolder = person;
    }

    public String getAcntName() {
        return acntName;
    }

    public void setAcntName(String acntName) {
        this.acntName = acntName;
    }

    @Id
    public Long getAcntNumber() {
        return acntNumber;
    }

    public void setAcntNumber(Long acntNumber) {
        this.acntNumber = acntNumber;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Transaction.class,
            mappedBy = "transAccount")
    public Set<Transaction> getAcntTransactions() {
        return acntTransactions;
    }

    public void setAcntTransactions(Set<Transaction> transactions) {
        Iterator<Transaction> i = transactions.iterator();
        while (i.hasNext()) {
            Transaction t = i.next();
            if (t.getTransType().equals("credit")) {
                this.creditAmount(t.getTransAmount());
            } else if (t.getTransType().equals("debit")) {
                this.debitAmount(t.getTransAmount());
            }
        }
        this.acntTransactions = transactions;
    }

    public String getAcntType() {
        return acntType;
    }

    public void setAcntType(String acntType) {
        this.acntType = acntType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
