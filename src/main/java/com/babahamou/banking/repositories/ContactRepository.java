package com.babahamou.banking.repositories;

import com.babahamou.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {



}
