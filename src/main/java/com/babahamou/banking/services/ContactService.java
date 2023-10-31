package com.babahamou.banking.services;

import com.babahamou.banking.dtos.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findByUserId(Integer userId);
}
