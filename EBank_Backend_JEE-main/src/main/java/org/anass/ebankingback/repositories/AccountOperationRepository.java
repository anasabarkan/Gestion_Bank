package org.anass.ebankingback.repositories;

import org.anass.ebankingback.entities.AccountOperation;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
     List<AccountOperation> findByBankAccountId(String accountId);
     Page<AccountOperation> findByBankAccountId(String accountId , Pageable pageable);
}
