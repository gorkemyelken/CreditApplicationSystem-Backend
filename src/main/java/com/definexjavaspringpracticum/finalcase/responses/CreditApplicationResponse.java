package com.definexjavaspringpracticum.finalcase.responses;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;

import javax.validation.constraints.NotNull;
import java.sql.Date;

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
