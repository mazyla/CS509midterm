package com.cs509.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccount extends Account {
    private TransactionService transactionService;

    private double balance;

    @Autowired
    public CustomerAccount(TransactionService transactionService) {
        super();
        this.transactionService = transactionService;
    }

    public void deposit(double amount) {
        Transaction depositTransaction = new Deposit(amount);
        if (transactionService.executeTransaction(depositTransaction)) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            // Here, you can add logic to record the transaction
            return true;
        }
        return false;
    }
}
