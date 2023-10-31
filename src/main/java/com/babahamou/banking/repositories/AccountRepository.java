package com.babahamou.banking.repositories;

import com.babahamou.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {


    Optional<Object> findByIban(String iban);
}