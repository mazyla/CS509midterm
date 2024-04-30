package com.cs509.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {
    public Deposit(Account account, double amount) {
        super(account, amount);
    }
}