package com.definexjavaspringpracticum.finalcase.repositories;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    boolean existsByCreditApplicationId(Long creditApplicationId);

    CreditApplication findByCreditApplicationId(Long creditApplicationId);

    @Query(value = "select c.confirmation_information, c.limit, c.create_date, u.identity_number, u.first_name, u.last_name, c.customer_monthly_income, u.phone_number, u.birth_date, c.customer_credit_score"
            +" from credit_application c inner join customer u on c.customer_id = u.customer_id where u.identity_number = :identityNumber and u.birth_date = :birthDate",nativeQuery = true)
    List<Object> findByIdentityNumberAndBirthDate(@Param("identityNumber") String identityNumber, @Param("birthDate") Date birthDate);
}
