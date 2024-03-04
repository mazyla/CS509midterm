package com.cs509.models;

public class Withdraw extends Transaction {
    public Withdraw(double amount) {
        super("WITHDRAW", amount);
    }

    @Override
    public boolean execute() {
        // Logic to execute a withdrawal
        return true;
    }
}
