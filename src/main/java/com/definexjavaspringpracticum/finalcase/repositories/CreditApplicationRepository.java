package com.definexjavaspringpracticum.finalcase.repositories;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    boolean existsByCreditApplicationId(Long creditApplicationId);

    CreditApplication findByCreditApplicationId(Long creditApplicationId);

    @Query(value = "select c.credit_application_id, c.confirmation_information, c.create_date, c.limit from credit_application c inner join customer u on c.customer_id = u.customer_id where u.identity_number = :identityNumber and u.birth_date = :birthDate",nativeQuery = true)
    Object findByIdentityNumberAndBirthDate(@Param("identityNumber") String identityNumber,@Param("birthDate") Date birthDate);
}
