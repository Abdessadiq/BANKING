package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.UserDto;
import com.babahamou.banking.models.User;
import com.babahamou.banking.repositories.UserRepository;
import com.babahamou.banking.services.UserService;
import com.babahamou.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        User userSaved = repository.save(user);
        return userSaved.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No user was found with the provided ID : "+ id));
    }
    @Override
    public void delete(Integer id) {

        // todo check before delete..
        repository.deleteById(id);

    }
}
