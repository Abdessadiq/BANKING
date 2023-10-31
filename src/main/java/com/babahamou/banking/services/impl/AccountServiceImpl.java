package com.babahamou.banking.services.impl;

import com.babahamou.banking.dtos.AccountDto;
import com.babahamou.banking.exceptions.OperationNonPermittedException;
import com.babahamou.banking.models.Account;
import com.babahamou.banking.repositories.AccountRepository;
import com.babahamou.banking.services.AccountService;
import com.babahamou.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ObjectsValidator<AccountDto> validator;
    private final AccountRepository accountRepository;
    @Override
    public Integer save(AccountDto dto) {
      /*  if (dto.getId() != null){
            throw new OperationNonPermittedException(
                    "account cannot be updated..",
                    "save account..",
                    "account..",
                    "updated not permitted.."
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount){
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account..",
                    "Account service",
                    "Account Creation"
            );
        }

        // generate random IBAN when creating new account else do not update the IBAN..
        if (dto.getId() == null){
        account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream().map(AccountDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id).map(AccountDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No account was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check account to delete
        accountRepository.deleteById(id);

    }

    private String generateRandomIban(){
        // generate iban
        String iban = Iban.random(CountryCode.MR).toFormattedString();
        //check if iban already exist
        boolean ibanExist = accountRepository.findByIban(iban).isPresent();

        // if exist --> Generale new iban
        if (ibanExist){
            generateRandomIban();
        }
        // if not exist --> return generated iban
        return iban;
    }
}
