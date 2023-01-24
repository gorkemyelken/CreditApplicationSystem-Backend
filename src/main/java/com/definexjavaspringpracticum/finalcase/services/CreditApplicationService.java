package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorDataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {
    private final Date date;
    private final CreditApplicationRepository creditApplicationRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CreditApplicationService(CreditApplicationRepository creditApplicationRepository, ModelMapperService modelMapperService) {
        this.creditApplicationRepository = creditApplicationRepository;
        this.modelMapperService = modelMapperService;
        this.date = Date.valueOf(LocalDate.now());
    }

    public DataResult<List<CreditApplicationResponse>> getAllCreditApplications() {
        List<CreditApplication> creditApplications = this.creditApplicationRepository.findAll();
        List<CreditApplicationResponse> result = creditApplications.stream().map(creditApplication -> this.modelMapperService.forDto().map(creditApplication,CreditApplicationResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(result,"Credit applications are listed.");
    }

    public DataResult<CreditApplicationResponse> createCreditApplication(CreditApplicationCreateRequest creditApplicationCreateRequest){
        if(!checkIfCreditApplicationCustomerExist(creditApplicationCreateRequest.getCustomer())){
            return new ErrorDataResult<>("Customer is not found.");
        }
        return new SuccessDataResult<>(new CreditApplicationResponse(Objects.requireNonNull(creditConditions(creditApplicationCreateRequest))),"Credit application is added.");
    }



    private CreditApplication creditConditions(CreditApplicationCreateRequest creditApplicationCreateRequest) {
        CreditApplication creditApplication = this.creditApplicationRepository.save(new CreditApplication(this.date));
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
                && (creditApplicationCreateRequest.getCustomer().getMonthlyIncome() > 10000)) {
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit(creditApplicationCreateRequest.getCustomer().getMonthlyIncome() * 4 / 2);
            return creditApplication;
        }
        //If the credit score is equal to or above 1000 points, the user will be approved and the user is assigned a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER. (Credit Result: Approved)
        else if((creditApplicationCreateRequest.getCustomer().getCreditScore() >= 1000)){
            creditApplication.setConfirmationInformation("Approved.");
            creditApplication.setCustomer(creditApplicationCreateRequest.getCustomer());
            creditApplication.setLimit(creditApplication.getCustomer().getMonthlyIncome() * 4);
            return creditApplication;
        }
        return null;
    }

    public DataResult<CreditApplicationResponse> delete(Long creditApplicationId){
        if(!checkIfCreditApplicationIdExist(creditApplicationId)){
            return new ErrorDataResult<>("Credit application id is not found.");
        }
        else{
            CreditApplication creditApplication = this.creditApplicationRepository.findByCreditApplicationId(creditApplicationId);
            this.creditApplicationRepository.deleteById(creditApplicationId);
            return new SuccessDataResult<>(new CreditApplicationResponse(creditApplication),"Credit application is deleted.");
        }
    }

    private boolean checkIfCreditApplicationIdExist(Long creditApplicationId) {
        return this.creditApplicationRepository.existsByCreditApplicationId(creditApplicationId);
    }

    private boolean checkIfCreditApplicationCustomerExist(Customer customer) {
        return this.creditApplicationRepository.existsByCustomer(customer);
    }
}
