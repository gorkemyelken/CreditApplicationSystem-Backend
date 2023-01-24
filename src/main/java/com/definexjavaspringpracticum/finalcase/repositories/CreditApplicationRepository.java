package com.definexjavaspringpracticum.finalcase.repositories;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    boolean existsByCreditApplicationId(Long creditApplicationId);

    CreditApplication findByCreditApplicationId(Long creditApplicationId);

    boolean existsByCustomer(Customer customer);
}
