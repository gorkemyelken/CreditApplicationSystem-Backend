package com.definexjavaspringpracticum.finalcase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {
    @NotNull
    @NotBlank
    String identityNumber;

    @NotNull
    @NotBlank
    String firstName;

    @NotNull
    @NotBlank
    String lastName;

    @NotNull
    Double monthlyIncome;

    @NotNull
    @NotBlank
    String phoneNumber;

    @NotNull
    Date birthDate;
}
