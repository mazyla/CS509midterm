package com.cs509.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs509.models.Account;
import com.cs509.models.Deposit;
import com.cs509.models.Transaction;
import com.cs509.models.TransactionType;
import com.cs509.models.Withdraw;
import com.cs509.repositories.AccountRepository;
import com.cs509.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void createTransaction(Account account, double amount, TransactionType type) {
        Transaction transaction = null;
        switch (type) {
            case DEPOSIT:
                account.setBalance(account.getBalance() + amount);
                transaction = new Deposit(amount);
                break;
            case WITHDRAW:
                account.setBalance(account.getBalance() - amount);
                transaction = new Withdraw(amount);
                break;
            default:
                break;
        }
        transaction.setAccount(account);
        transaction.setTransactionDate(new Date());
        accountRepository.save(account);
        transactionRepository.save(transaction);
        System.out.println(type + " successful.");
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Date: " + transaction.getTransactionDate());
        System.out.println("Balance: " + account.getBalance());
        return;
    }
}