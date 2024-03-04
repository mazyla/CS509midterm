# CS509midterm

Midterm work for CS509
Mario Zyla
March 3rd, 2024

# Use cases

Please find use-cases in [use-cases.md](written-part/use-cases.md)

# Class Diagrams

Please find class diagrams in [class-diagrams-visual.png](written-part/class-diagrams/class-diagrams-visual.png)

There are some differences between what I wrote in code and what I have here. That's because I created the diagrams and then build the app, and had to modify some things. Ideally, I would come back and update this and I tried, but I am running out of time now. The differences are small and have to do more with the arguments passed into the functions.

# Code

## Database initialization

```
CREATE TABLE account (
    account_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    account_type ENUM('CUSTOMER', 'ADMINISTRATOR') NOT NULL
);

CREATE TABLE transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id VARCHAR(255),
    transaction_type ENUM('DEPOSIT', 'WITHDRAW') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- Insert accounts
INSERT INTO account (account_id, name, password, balance, account_type) VALUES ('user1', 'John Doe', 'password', 1000.00, 'CUSTOMER');
INSERT INTO account (account_id, name, password, balance, account_type) VALUES ('admin1', 'Admin User', 'adminpass', 0.00, 'ADMINISTRATOR');

-- Insert transactions
INSERT INTO transaction (account_id, transaction_type, amount) VALUES ('user1', 'DEPOSIT', 500.00);
INSERT INTO transaction (account_id, transaction_type, amount) VALUES ('user1', 'WITHDRAW', 200.00);
```

# References

1. Setting up Spring Boot: https://spring.io/guides/gs/spring-boot
2. Setting up Entity Framework: https://www.baeldung.com/spring-boot-hibernate (although, I got a lot of issues with this one, had to Google a lot of the errors)
3. Dependency injection: https://medium.com/@reetesh043/spring-boot-dependency-injection-137f85f84590
