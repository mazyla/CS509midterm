package com.cs509.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    protected final Date transactionDate;
    protected final double amount;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    protected final Account account;

    public Transaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
        this.transactionDate = new Date(); // Capture transaction time at creation
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }
}
