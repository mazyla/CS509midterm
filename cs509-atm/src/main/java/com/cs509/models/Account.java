package com.cs509.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs509.exceptions.AccountNotFoundException;
import com.cs509.repositories.AccountRepository;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    private String accountId;
    public String name;
    private String password;
    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public AccountType getAccountType() {
        return this.accountType;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public Account() {
    }

    // public static Account login(String accountId, String password) throws
    // Exception {
    // Account account = accountRepository.findByUsernameAndPassword(accountId,
    // password);
    // if (account != null) {
    // // Assuming AccountType is an enum in Account class
    // switch (account.getAccountType()) {
    // case CUSTOMER:
    // // Ensure CustomerAccount extends Account and matches the constructor
    // CustomerAccount customerAccount = new CustomerAccount();
    // return customerAccount;
    // case ADMINISTRATOR:
    // // Ensure AdministratorAccount extends Account and matches the constructor
    // return new AdministratorAccount();
    // default:
    // throw new AccountNotFoundException("Unknown account type");
    // }
    // } else {
    // throw new AccountNotFoundException("Authentication failed");
    // }
    // }

    public void AddBalance(double amount) {
        this.balance += amount;
    }
}
