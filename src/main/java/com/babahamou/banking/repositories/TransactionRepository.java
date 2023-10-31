package com.babahamou.banking.repositories;

import com.babahamou.banking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {



}
