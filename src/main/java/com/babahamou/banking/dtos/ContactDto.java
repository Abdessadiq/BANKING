package com.babahamou.banking.dtos;


import com.babahamou.banking.models.Contact;
import com.babahamou.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        // null check
        return  ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }


    public static Contact toEntity(ContactDto contact){
        // null check
        return  Contact.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(User.builder().id(contact.getUserId()).build())
                .build();
    }

}
