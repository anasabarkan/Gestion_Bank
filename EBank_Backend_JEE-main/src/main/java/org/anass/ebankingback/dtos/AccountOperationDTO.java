package org.anass.ebankingback.dtos;

import lombok.*;
import org.anass.ebankingback.enums.OperationType;
import org.anass.ebankingback.entities.*;
import org.anass.ebankingback.enums.*;

import java.util.*;

@Data
public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private double amount ;
    private OperationType type;
    private String description;


}
