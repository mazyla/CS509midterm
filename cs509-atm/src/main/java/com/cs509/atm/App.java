package com.cs509.atm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // CustomerAccount account = context.getBean(CustomerAccount.class);
        // account.deposit(500);
        // account.withdraw(200);

        context.close();
    }
}
