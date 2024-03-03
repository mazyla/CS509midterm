package com.cs509.atm;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public boolean executeTransaction(Transaction transaction) {
        // Logic to execute transaction (e.g., updating account balance, recording the
        // transaction)
        System.out
                .println("Executing transaction: " + transaction.getType() + " for amount " + transaction.getAmount());
        return true;
    }
}