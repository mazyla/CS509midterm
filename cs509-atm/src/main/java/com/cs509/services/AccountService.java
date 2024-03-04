package com.cs509.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs509.exceptions.AccountNotFoundException;
import com.cs509.models.Account;
import com.cs509.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account login(String username, String password) throws AccountNotFoundException {
        return accountRepository.findByAccountIdAndPassword(username, password)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with username: " + username));
    }
}
