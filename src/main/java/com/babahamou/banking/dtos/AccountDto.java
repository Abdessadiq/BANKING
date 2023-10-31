package com.babahamou.banking.dtos;


import com.babahamou.banking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class AccountDto {

    private Integer id;
    private String iban;
    private UserDto user;

    /**
     * @param account
     * @return AccountDto
     */
    public static AccountDto fromEntity(Account account){

        //null Check..
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }
    public static Account toEntity(AccountDto account){

        //null Check..
        return Account.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.toEntity(account.getUser()))
                .build();
    }
}
