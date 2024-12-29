package org.anass.ebankingback.repositories;

import org.anass.ebankingback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
