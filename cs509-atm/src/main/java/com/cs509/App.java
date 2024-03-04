package com.cs509;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cs509.exceptions.AccountNotFoundException;
import com.cs509.models.Account;
import com.cs509.models.AccountType;
import com.cs509.models.CustomerAccount;
import com.cs509.services.AccountService;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
