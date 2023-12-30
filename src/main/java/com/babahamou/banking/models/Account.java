package com.babahamou.banking.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Account extends AbstractEntity{

    private String iban;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
