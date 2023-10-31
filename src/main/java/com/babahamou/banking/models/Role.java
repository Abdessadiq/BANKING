package com.babahamou.banking.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Role extends AbstractEntity{

    private String roleName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
