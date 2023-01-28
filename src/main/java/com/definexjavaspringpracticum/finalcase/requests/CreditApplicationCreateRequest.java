package com.definexjavaspringpracticum.finalcase.requests;

import com.definexjavaspringpracticum.finalcase.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationCreateRequest {
    @NotNull
    private Customer customer;
}
