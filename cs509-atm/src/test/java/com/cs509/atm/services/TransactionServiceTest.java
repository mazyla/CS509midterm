package com.cs509.atm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cs509.models.Account;
import com.cs509.models.Deposit;
import com.cs509.models.TransactionType;
import com.cs509.models.Withdraw;
import com.cs509.repositories.AccountRepository;
import com.cs509.repositories.TransactionRepository;
import com.cs509.services.AccountService;
import com.cs509.services.TransactionService;

@SpringBootTest(classes = { AccountService.class, TransactionService.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    private Account setupAccount() {
        Account account = new Account();
        account.setAccountId("12345");
        account.setBalance(1000.0);
        return account;
    }

    @Test
    void testCreateDepositTransaction() throws Exception {
        Account account = setupAccount();
        double depositAmount = 200.0;
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        transactionService.createTransaction(account, depositAmount, TransactionType.DEPOSIT);

        assertEquals(1200.0, account.getBalance(), "Balance should be correctly updated.");
        verify(transactionRepository).save(any(Deposit.class));
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateWithdrawTransaction() throws Exception {
        Account account = setupAccount();
        double withdrawAmount = 150.0;
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        transactionService.createTransaction(account, withdrawAmount, TransactionType.WITHDRAW);

        assertEquals(850.0, account.getBalance(), "Balance should be correctly updated.");
        verify(transactionRepository).save(any(Withdraw.class));
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateUnsupportedTransactionType() {
        Account account = setupAccount();
        double amount = 100.0;

        assertThrows(Exception.class, () -> {
            transactionService.createTransaction(account, amount, null);
        });
    }

}
