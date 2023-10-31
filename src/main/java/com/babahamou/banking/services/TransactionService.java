package com.babahamou.banking.services;

import com.babahamou.banking.dtos.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{
    List<TransactionDto> findAllByUserId(Integer userId);
}
