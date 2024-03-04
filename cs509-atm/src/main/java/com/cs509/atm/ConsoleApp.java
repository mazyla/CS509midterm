package com.cs509.atm;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cs509.models.Account;
import com.cs509.services.AccountService;

@Component
public class ConsoleApp implements CommandLineRunner {

    private final AccountService accountService;

    public ConsoleApp(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        TransactionService transactionService = new TransactionServiceImpl();

        System.out.println("Welcome to the ATM System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            Account account = accountService.login(username, password);
            switch (account.getAccountType()) {
                case CUSTOMER:
                    System.out.println("I'm here");
                    break;
                case ADMINISTRATOR:
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
