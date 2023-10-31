package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.TransactionDto;
import com.babahamou.banking.models.Transaction;
import com.babahamou.banking.models.TransactionType;
import com.babahamou.banking.repositories.TransactionRepository;
import com.babahamou.banking.services.TransactionService;
import com.babahamou.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService  {

    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier =  BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no transaction was found for id : "+id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete..
        repository.deleteById(id);

    }

    private int getTransactionMultiplier(TransactionType type){
        return TransactionType.TRANSFER == type ? -1 : 1;
    }
}
