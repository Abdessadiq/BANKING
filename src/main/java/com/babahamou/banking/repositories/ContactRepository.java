package com.babahamou.banking.repositories;

import com.babahamou.banking.dtos.ContactDto;
import com.babahamou.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {


    List<Contact> findAllByUserId(Integer userId);
}
