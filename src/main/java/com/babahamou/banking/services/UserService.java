package com.babahamou.banking.services;

import com.babahamou.banking.dtos.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
