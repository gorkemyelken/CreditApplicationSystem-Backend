package com.definexjavaspringpracticum.finalcase.requests;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationCreateRequest {
    @NotNull
    private Customer customer;
}
