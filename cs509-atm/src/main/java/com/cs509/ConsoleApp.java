package com.cs509;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cs509.models.Account;
import com.cs509.models.TransactionType;
import com.cs509.services.AccountService;
import com.cs509.services.TransactionService;

@Component
public class ConsoleApp implements CommandLineRunner {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public ConsoleApp(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            Account account = accountService.login(username, password);
            switch (account.getAccountType()) {
                case CUSTOMER:
                    boolean done = false;
                    while (!done) {
                        System.out.println(
                                "Select an option: \n1. Deposit Cash \n2. Withdraw Cash \n3. Check Balance \n4. Exit");
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                // Deposit cash
                                System.out.print("Enter amount to deposit: ");
                                double depositAmount = scanner.nextDouble();
                                scanner.nextLine();
                                transactionService.createTransaction(account, depositAmount, TransactionType.DEPOSIT);
                                break;
                            case 2:
                                // Withdraw cash
                                System.out.print("Enter amount to withdraw: ");
                                double withdrawAmount = scanner.nextDouble();
                                scanner.nextLine();
                                transactionService.createTransaction(account, withdrawAmount, TransactionType.WITHDRAW);
                                System.out.println("Withdrawn successfully.");
                                break;
                            case 3:
                                // Check balance
                                System.out.println("Current balance: " + account.getBalance());
                                break;
                            case 4:
                                // Exit
                                System.out.println("Exiting...");
                                done = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                                break;
                        }
                    }
                    break;
                case ADMINISTRATOR:
                    done = false;
                    while (!done) {
                        System.out.println(
                                "Select an option: \n1. Create New Account \n2. Delete Existing Account \n3. Update Account Information \n4. Search for Account \n5. Exit");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                // Create New Account
                                System.out.print("Enter account id: ");
                                String accountIdToCreate = scanner.nextLine();
                                System.out.print("Enter account name: ");
                                String nameToCreate = scanner.nextLine();
                                String newPassword;
                                while (true) {
                                    System.out.print("Enter account password: ");
                                    newPassword = scanner.nextLine();
                                    if (newPassword.length() > 5) {
                                        break; // Exit loop if password length is greater than 5
                                    } else {
                                        System.out.println(
                                                "Password must be longer than 5 characters. Please try again.");
                                    }
                                }
                                System.out.print("Enter account balance: ");
                                double newBalance = scanner.nextDouble();
                                scanner.nextLine();
                                accountService.createAccount(accountIdToCreate, nameToCreate, newPassword, newBalance);
                                break;
                            case 2:
                                // Delete account
                                System.out.print("Enter account username: ");
                                String accountIdToDelete = scanner.nextLine();
                                Account accountToDelete = accountService.findById(accountIdToDelete);
                                System.out.print("If you still want to delete the account of "
                                        + accountToDelete.getName() + " reenter the account id: ");
                                String accountIdConfirmation = scanner.nextLine();
                                if (accountIdToDelete.equals(accountIdConfirmation)) {
                                    accountService.deleteAccount(accountToDelete);
                                    System.out.println("Deleted successfully.");
                                }
                                break;
                            case 3:
                                // Update Account Information
                                System.out.print("Enter account username: ");
                                String accountIdToUpdate = scanner.nextLine();

                                System.out.print("Enter new account name: ");
                                String nameToUpdate = scanner.nextLine();
                                String passwordToUpdate;
                                while (true) {
                                    System.out.print("Enter account password: ");
                                    passwordToUpdate = scanner.nextLine();
                                    if (passwordToUpdate.length() > 5) {
                                        break; // Exit loop if password length is greater than 5
                                    } else {
                                        System.out.println(
                                                "Password must be longer than 5 characters. Please try again.");
                                    }
                                }
                                System.out.print("Enter account balance: ");
                                double balanceToUpdate = scanner.nextDouble();
                                scanner.nextLine();
                                accountService.updateAccount(accountIdToUpdate, nameToUpdate, passwordToUpdate,
                                        balanceToUpdate);
                                System.out.println("Update successful");
                                break;
                            case 4:
                                // Search For Account
                                System.out.print("Enter account username: ");
                                String accountIdToPull = scanner.nextLine();

                                Account accountToShow = accountService.findById(accountIdToPull);
                                System.out.print("account name: " + accountToShow.getName() + "\n");
                                System.out.print("account balance: " + accountToShow.getBalance() + "\n");
                                System.out.print("account password: " + accountToShow.getPassword() + "\n");
                                break;
                            case 5:
                                // Exit
                                done = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                                break;
                        }
                    }
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
