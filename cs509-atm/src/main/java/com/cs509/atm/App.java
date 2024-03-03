package com.cs509.atm;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TransactionService transactionService = new TransactionServiceImpl();

        System.out.println("Welcome to the ATM System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            AccountType accountType = Account.login(username, password);
            switch (accountType) {
                case CUSTOMER:
                    CustomerAccount customerAccount = new CustomerAccount(transactionService);
                    System.out.println("Logged in as Customer. Welcome, " + username + "!");
                    // Perform customer-specific actions
                    break;
                default:
                    break;
                // case ADMINISTRATOR:
                // AdministratorAccount administratorAccount = new
                // AdministratorAccount(username, 0, password,
                // transactionService);
                // System.out.println("Logged in as Administrator. Welcome, " + username + "!");
                // // Perform administrator-specific actions
                // break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
