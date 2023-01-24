package com.definexjavaspringpracticum.finalcase.responses;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    Long customerId;
    String identityNumber;
    String firstName;
    String lastName;
    Double monthlyIncome;
    String phoneNumber;
    Date birthDate;
    int creditScore;
    public CustomerResponse(Customer customer){
        this.customerId = customer.getCustomerId();
        this.identityNumber = customer.getIdentityNumber();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.monthlyIncome = customer.getMonthlyIncome();
        this.phoneNumber = customer.getPhoneNumber();
        this.birthDate = customer.getBirthDate();
        this.creditScore = customer.getCreditScore();
    }
}
