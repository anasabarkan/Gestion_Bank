package org.anass.ebankingback.web;

import lombok.*;
import lombok.extern.slf4j.*;
import org.anass.ebankingback.dtos.CustomerDTO;
import org.anass.ebankingback.exeption.CustomerNotFoundException;
import org.anass.ebankingback.services.BankAccountService;
import org.anass.ebankingback.dtos.*;
import org.anass.ebankingback.entities.*;
import org.anass.ebankingback.exeption.*;
import org.anass.ebankingback.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId ,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }
}
