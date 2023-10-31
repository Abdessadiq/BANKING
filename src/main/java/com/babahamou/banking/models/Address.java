package com.babahamou.banking.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
@Entity
public class Address  extends AbstractEntity{

    private String street;
    private Integer zipCode;
    private Integer houseNumber;
    private String city;
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
