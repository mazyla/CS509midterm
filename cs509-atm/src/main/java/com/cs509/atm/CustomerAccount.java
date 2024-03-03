package com.cs509.atm;

public class CustomerAccount extends Account {
    private TransactionService transactionService;

    private double balance;

    // Adjusted constructor to include TransactionService
    public CustomerAccount(int accountId, TransactionService transactionService) {
        super(accountId);
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
