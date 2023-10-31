package com.babahamou.banking.dtos;


import com.babahamou.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class UserDto {


    private Integer id;

    @NotNull @NotEmpty @NotBlank
    private String firstName;

    @NotNull (message = "Le Nom ne doit pas être vide")
    @NotEmpty (message = "Le nom ne doit pas être vide")
    @NotBlank (message = "Le nom ne doit pas être vide")
    private String lastName;

    @NotNull @NotEmpty @NotBlank
    @Email
    private String email;

    @NotNull @NotEmpty @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    /**
     *
     * @param user
     * @return UserDto
     */

    public static UserDto fromEntity(User user){
        // null check
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user){
        // null check
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
