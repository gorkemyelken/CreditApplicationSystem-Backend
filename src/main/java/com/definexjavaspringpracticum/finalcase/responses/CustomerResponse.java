package com.definexjavaspringpracticum.finalcase.responses;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import lombok.Data;

import java.sql.Date;
@Data
public class CustomerResponse {
    Long id;
    String identityNumber;
    String firstName;
    String lastName;
    Double monthlyIncome;
    String phoneNumber;
    Date birthDate;

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.identityNumber = customer.getIdentityNumber();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.monthlyIncome = customer.getMonthlyIncome();
        this.phoneNumber = customer.getPhoneNumber();
        this.birthDate = customer.getBirthDate();
    }
}
