package org.anass.ebankingback;
import org.anass.ebankingback.dtos.BankAccountDTO;
import org.anass.ebankingback.dtos.CurrentBankAccountDTO;
import org.anass.ebankingback.dtos.CustomerDTO;
import org.anass.ebankingback.dtos.SavingBankAccountDTO;
import org.anass.ebankingback.entities.AccountOperation;
import org.anass.ebankingback.entities.CurrentAccount;
import org.anass.ebankingback.entities.Customer;
import org.anass.ebankingback.entities.SavingAccount;
import org.anass.ebankingback.exeption.CustomerNotFoundException;
import org.anass.ebankingback.services.BankAccountService;
import org.anass.ebankingback.enums.AccountStatus;
import org.anass.ebankingback.enums.OperationType;
import org.anass.ebankingback.repositories.AccountOperationRepository;
import org.anass.ebankingback.repositories.BankAccountRepository;
import org.anass.ebankingback.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingBackApplication.class, args);
	}
	@Bean

	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			Stream.of("oussama","yassine","hicham").forEach(name->{
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");

				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000,customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.4, customer.getId());

				} catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
			for (BankAccountDTO bankAccount:bankAccounts) {
				for (int i = 0; i <10 ; i++) {
					String accountId;
					if (bankAccount instanceof SavingBankAccountDTO){
						accountId=((SavingBankAccountDTO) bankAccount).getId();
					}else{
						accountId=((CurrentBankAccountDTO) bankAccount).getId();

					}
					bankAccountService.credit(accountId, 10000+Math.random()*120000,"Credit");
					bankAccountService.debit(accountId, 1000+Math.random()*8000,"Debit");
				}
			}
		};
	}
//	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args -> {
			Stream.of("oussama","hakim","hicham").forEach(name->{
				Customer customer =new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(custom->{
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*3000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(custom);
				currentAccount.setOverDraft(5000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*3000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(custom);
				savingAccount.setInterestRate(4.2);
				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(acc->{
				for (int i=0 ;i<8;i++){
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*10000);
					accountOperation.setType(Math.random()>0.5 ? OperationType.DEBIT :OperationType.CREDIT );
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);
				}
			});


		};

	}
}
