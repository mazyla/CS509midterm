package com.cs509.atm;

public class Account {
    private int accountId;
    public String name;
    private String password;

    public Account(int accountId) {
        this.accountId = accountId;
    }

    public Account login(String name, String password) throws Exception {
        // TODO: check the db for this name and password
        // TODO: Load the correct type of Account
        return this;
    }
}
