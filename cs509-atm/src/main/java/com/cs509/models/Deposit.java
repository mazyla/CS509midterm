package com.cs509.models;

public class Deposit extends Transaction {
    public Deposit(double amount) {
        super("DEPOSIT", amount);
    }

    @Override
    public boolean execute() {
        // Logic to execute a deposit
        return true;
    }
}