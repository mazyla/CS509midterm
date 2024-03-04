package com.cs509.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs509.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}