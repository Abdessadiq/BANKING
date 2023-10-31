package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.AddressDto;
import com.babahamou.banking.models.Address;
import com.babahamou.banking.repositories.AddressRepository;
import com.babahamou.banking.services.AddressService;
import com.babahamou.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private ObjectsValidator<AddressDto> validator;
    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream().map(AddressDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id).map(AddressDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No Address was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        repository.deleteById(id);

    }
}
