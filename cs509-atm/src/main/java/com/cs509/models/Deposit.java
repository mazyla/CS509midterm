package com.cs509.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {
    public Deposit(double amount) {
        super(amount);
    }
}