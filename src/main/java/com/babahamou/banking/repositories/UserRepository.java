package com.babahamou.banking.repositories;

import com.babahamou.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
