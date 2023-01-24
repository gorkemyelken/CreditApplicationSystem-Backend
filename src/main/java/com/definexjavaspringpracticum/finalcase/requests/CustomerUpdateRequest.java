package com.definexjavaspringpracticum.finalcase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    String identityNumber;
    String firstName;
    String lastName;
    Double monthlyIncome;
    String phoneNumber;
    Date birthDate;
}
