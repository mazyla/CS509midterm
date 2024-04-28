package com.cs509.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAW")
public class Withdraw extends Transaction {
    public Withdraw(Account account, double amount) {
        super(account, amount);
    }
}
