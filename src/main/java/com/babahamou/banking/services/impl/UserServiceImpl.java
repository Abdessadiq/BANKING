package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.AccountDto;
import com.babahamou.banking.dtos.UserDto;
import com.babahamou.banking.models.Account;
import com.babahamou.banking.models.User;
import com.babahamou.banking.repositories.UserRepository;
import com.babahamou.banking.services.AccountService;
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


    private final UserRepository userRepository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        User userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No user was found with the provided ID : "+ id));
    }
    @Override
    public void delete(Integer id) {

        // todo check before delete..
        userRepository.deleteById(id);

    }

    @Override
    public Integer validateAccount(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        // create a bank account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no user was found for user account validation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
