package com.cs509.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs509.models.Account;
import com.cs509.models.Deposit;
import com.cs509.models.Transaction;
import com.cs509.models.TransactionType;
import com.cs509.models.Withdraw;
import com.cs509.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Account account, double amount, TransactionType type) {
        Transaction transaction = null;
        switch (type) {
            case DEPOSIT:
                transaction = new Deposit(amount);
                break;
            case WITHDRAW:
                transaction = new Withdraw(amount);
                break;
        }
        transaction.setAccount(account);
        transaction.setTransactionDate(new Date());
        return transactionRepository.save(transaction);
    }
}