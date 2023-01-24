package com.definexjavaspringpracticum.finalcase.responses;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationResponse {
    private String confirmationInformation;

    private Double limit;

    private Date createDate;

    private Customer customer;

    public CreditApplicationResponse(CreditApplication creditApplication) {
        this.confirmationInformation = creditApplication.getConfirmationInformation();
        this.limit = creditApplication.getLimit();
        this.createDate = creditApplication.getCreateDate();
        this.customer = creditApplication.getCustomer();
    }
}
