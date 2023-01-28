package com.definexjavaspringpracticum.finalcase.repositories;

import com.definexjavaspringpracticum.finalcase.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long customerId);
    Customer findByIdentityNumber(String identityNumber);
    boolean existsByCustomerId(Long customerId);
    boolean existsByIdentityNumber(String identityNumber);
}
