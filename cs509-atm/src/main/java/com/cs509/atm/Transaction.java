package com.cs509.atm;

public abstract class Transaction {
    private int transactionId;
    private String date;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public abstract boolean execute();
}
