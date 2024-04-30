package com.cs509.atm.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cs509.models.Account;
import com.cs509.models.AccountType;
import com.cs509.models.AdministratorAccount;
import com.cs509.models.CustomerAccount;
import com.cs509.repositories.AccountRepository;
import com.cs509.services.AccountService;

import java.util.Optional;

@SpringBootTest(classes = AccountService.class)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private Account setupMockAccount(AccountType type) {
        Account account;

        if (type == AccountType.CUSTOMER) {
            account = new CustomerAccount();
        } else {
            account = new AdministratorAccount();
        }

        account.setAccountId("user1");
        account.setName("Test User");
        account.setPassword("pass123");
        account.setBalance(100.0);
        return account;
    }

    @Test
    void testLoginSuccess() throws Exception {
        String username = "user1";
        String password = "pass1";
        Account mockAccount = new Account();
        mockAccount.setAccountId(username);
        mockAccount.setPassword(password);
        mockAccount.setName(username);
        when(accountRepository.findByAccountIdAndPassword(username, password)).thenReturn(Optional.of(mockAccount));

        Account result = accountService.login(username, password);

        assertNotNull(result);
        assertEquals(username, result.getAccountId());
        assertEquals(username, result.getName());
        assertEquals(password, result.getPassword());
    }

    @Test
    void testLoginFailure() {
        String username = "user1";
        String password = "wrongpass";
        when(accountRepository.findByAccountIdAndPassword(username, password)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> accountService.login(username, password));
        assertTrue(exception.getMessage().contains("Account not found with username: " + username));
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        String accountId = "user1";
        Account mockAccount = setupMockAccount(AccountType.CUSTOMER);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(mockAccount));

        Account result = accountService.findById(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
    }

    @Test
    void testFindByIdNotFound() {
        String accountId = "user1";
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> accountService.findById(accountId));
        assertTrue(exception.getMessage().contains("Account not found with id: " + accountId));
    }

    @Test
    void testCreateAccount() {
        Account newAccount = setupMockAccount(AccountType.CUSTOMER);
        when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

        Account createdAccount = accountService.createAccount(newAccount.getAccountId(), newAccount.getName(),
                newAccount.getPassword(), newAccount.getBalance(), AccountType.CUSTOMER);

        assertNotNull(createdAccount);
        assertEquals(newAccount.getAccountId(), createdAccount.getAccountId());
        assertEquals(newAccount.getName(), createdAccount.getName());
        assertEquals(newAccount.getPassword(), createdAccount.getPassword());
        assertEquals(newAccount.getBalance(), createdAccount.getBalance(), 0.01);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void testUpdateAccount() throws Exception {
        String accountId = "existingUser";
        String newName = "Updated Name";
        String newPassword = "updatedPass123";
        double newBalance = 200.0;
        Account existingAccount = new Account();
        existingAccount.setAccountId(accountId);
        existingAccount.setName("Old Name");
        existingAccount.setPassword("oldPass123");
        existingAccount.setBalance(100.0);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        accountService.updateAccount(accountId, newName, newPassword, newBalance);

        assertEquals(newName, existingAccount.getName());
        assertEquals(newPassword, existingAccount.getPassword());
        assertEquals(newBalance, existingAccount.getBalance(), 0.01);
        verify(accountRepository).save(existingAccount);
    }

    @Test
    void testDeleteAccount() {
        Account account = new Account();
        account.setAccountId("deleteUser");
        doNothing().when(accountRepository).deleteById(any(String.class));

        accountService.deleteAccount(account);

        verify(accountRepository).deleteById(account.getAccountId());
    }
}
