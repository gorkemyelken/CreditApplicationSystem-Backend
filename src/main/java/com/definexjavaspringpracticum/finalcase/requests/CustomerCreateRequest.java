package com.definexjavaspringpracticum.finalcase.requests;

import lombok.Data;

import java.sql.Date;
@Data
public class CustomerCreateRequest {
    String identityNumber;
    String firstName;
    String lastName;
    Double monthlyIncome;
    String phoneNumber;
    Date birthDate;
}
