package org.anass.ebankingback.web;


import org.anass.ebankingback.dtos.AccountHistoryDTO;
import org.anass.ebankingback.dtos.AccountOperationDTO;
import org.anass.ebankingback.dtos.BankAccountDTO;
import org.anass.ebankingback.exeption.BankAccountNotFoundException;
import org.anass.ebankingback.services.BankAccountService;
import org.anass.ebankingback.dtos.*;
import org.anass.ebankingback.exeption.*;
import org.anass.ebankingback.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BankAccountRestController {

    private BankAccountService bankAccountService;


    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);

    }


    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }


    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name = "page",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

}
