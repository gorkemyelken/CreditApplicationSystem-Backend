package com.definexjavaspringpracticum.finalcase.services.constants;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;

import java.sql.Date;
import java.time.LocalDate;

public class CreditCondition {
    private final Date date = Date.valueOf(LocalDate.now());;

    public CreditApplication checkCreditCondition(CreditApplication creditApplication, CreditApplicationCreateRequest creditApplicationCreateRequest){
        creditApplication.setCreateDate(date);
        int creditLimitMultiplier = 4;

        //If the credit score is below 500, the user will be rejected. (Credit result: Rejected)
        if(creditApplicationCreateRequest.getCustomer().getCreditScore()<500){
            creditApplication.setConfirmationInformation("Rejected.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit((double)0);
            return creditApplication;
        }
        //If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the user will be approved and a limit of 10.000 TL is assigned to the user. (Credit Result: Approved)
        else if ((creditApplicationCreateRequest.getCustomer().getCreditScore() > 500)
                && (creditApplicationCreateRequest.getCustomer().getCreditScore() < 1000)
                && (creditApplicationCreateRequest.getCustomer().getMonthlyIncome() < 5000)) {
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit((double)10000);
            return creditApplication;
        }
        //If the credit score is between 500 points and 1000 points and the monthly income is between 5000 TL and 10,000 TL, the user will be approved and a 20,000 TL limit is assigned to the user. (Credit Result:Approved)
        else if((creditApplicationCreateRequest.getCustomer().getCreditScore() > 500)
                && (creditApplicationCreateRequest.getCustomer().getCreditScore() < 1000)
                && (creditApplicationCreateRequest.getCustomer().getMonthlyIncome() > 5000)
                && (creditApplicationCreateRequest.getCustomer().getMonthlyIncome() < 10000)){
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit((double)20000);
            return creditApplication;
        }
        //If the credit score is between 500 points and 1000 points and the monthly income is above 10.000 TL, the user will be approved and the user is assigned a limit of MONTHLY INCOME INFORMATION * CREDIT LIMIT MULTIPLIER/2. (Credit Result:Approved)
        else if ((creditApplicationCreateRequest.getCustomer().getCreditScore() > 500)
                && (creditApplicationCreateRequest.getCustomer().getCreditScore() < 1000)
                && (creditApplicationCreateRequest.getCustomer().getMonthlyIncome() >= 10000)) {
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit(creditApplicationCreateRequest.getCustomer().getMonthlyIncome() * creditLimitMultiplier / 2);
            return creditApplication;
        }
        //If the credit score is equal to or above 1000 points, the user will be approved and the user is assigned a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER. (Credit Result: Approved)
        else if((creditApplicationCreateRequest.getCustomer().getCreditScore() >= 1000)){
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit(creditApplication.getCustomer().getMonthlyIncome() * creditLimitMultiplier);
            return creditApplication;
        }
        return null;
    }
}
