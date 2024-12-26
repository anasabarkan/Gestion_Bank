package org.oussama.ebankingback.dtos;

import lombok.*;
import org.oussama.ebankingback.enums.*;

import java.util.*;


@Data
public class SavingBankAccountDTO extends BankAccountDTO{

    private String id;
    private double balance ;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;

}
