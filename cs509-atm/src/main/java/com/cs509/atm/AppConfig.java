package com.cs509.atm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TransactionService transactionService() {
        return new TransactionServiceImpl();
    }

    @Bean
    public CustomerAccount customerAccount(Integer accountId) {
        return new CustomerAccount(accountId, transactionService());
    }
}
