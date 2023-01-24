package com.definexjavaspringpracticum.finalcase.modals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String identityNumber;

    String firstName;

    String lastName;

    Double monthlyIncome;

    String phoneNumber;

    Date birthDate;

    public Customer(String identityNumber, String firstName, String lastName, Double monthlyIncome, String phoneNumber, Date birthDate) {
        this.identityNumber = identityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}
