package com.cs509.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs509.models.Account;
import com.cs509.models.CustomerAccount;
import com.cs509.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account login(String username, String password) throws Exception {
        return accountRepository.findByAccountIdAndPassword(username, password)
                .orElseThrow(() -> new Exception("Account not found with username: " + username));
    }

    public Account findById(String accountId) throws Exception {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account not found with id: " + accountId));
    }

    public Account createAccount(String username, String name, String password, double balance) {
        Account account = new CustomerAccount();
        account.setBalance(balance);
        account.setAccountId(username);
        account.setName(name);
        account.setPassword(password);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public void deleteAccount(Account account) {
        accountRepository.deleteById(account.getAccountId());
    }

    public void updateAccount(String accountId, String name, String password, double balance)
            throws Exception {
        Account account = findById(accountId);
        account.setBalance(balance);
        account.setName(name);
        account.setPassword(password);
        accountRepository.save(account);
        return;
    }
}
