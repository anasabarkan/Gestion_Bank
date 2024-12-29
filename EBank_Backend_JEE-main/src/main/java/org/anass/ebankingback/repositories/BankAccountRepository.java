package org.anass.ebankingback.repositories;

import org.anass.ebankingback.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
