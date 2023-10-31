package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.ContactDto;
import com.babahamou.banking.models.Contact;
import com.babahamou.banking.repositories.ContactRepository;
import com.babahamou.banking.services.ContactService;
import com.babahamou.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {

        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll().stream().map(ContactDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id).map(ContactDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No Contact was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        repository.deleteById(id);

    }
}
