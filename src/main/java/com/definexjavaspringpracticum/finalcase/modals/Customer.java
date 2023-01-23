package com.definexjavaspringpracticum.finalcase.modals;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "customer")
@Data
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
}
