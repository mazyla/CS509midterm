package com.cs509.atm;

public class Withdraw extends Transaction {
    public Withdraw(int transactionId, String date, double amount) {
        super("WITHDRAW", amount);
    }

    @Override
    public boolean execute() {
        // Logic to execute a withdrawal
        return true;
    }
}
