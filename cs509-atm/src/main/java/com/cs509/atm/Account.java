package com.cs509.atm;

public class Account {
    private String accountId;
    public String name;
    private String password;

    public Account() {
    }

    public static AccountType login(String name, String password) throws Exception {
        return AccountType.CUSTOMER;
    }
}
