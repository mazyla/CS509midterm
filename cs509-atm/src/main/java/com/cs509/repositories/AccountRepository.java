package com.cs509.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs509.models.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountIdAndPassword(String accountId, String password);
}