package org.anass.ebankingback.entities;

import jakarta.persistence.*;
import org.anass.ebankingback.enums.OperationType;
import org.anass.ebankingback.enums.*;

import java.util.*;

import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount ;
    @Enumerated(EnumType.STRING) // par defaul => ORDINAL
    private OperationType type;
    private String description;
    @ManyToOne
    private BankAccount bankAccount;


}
