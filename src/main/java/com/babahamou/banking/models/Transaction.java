package com.babahamou.banking.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Transaction extends AbstractEntity{

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
