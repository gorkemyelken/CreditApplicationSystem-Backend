package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.services.constants.CreditCondition;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorDataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {
    private final Date date;
    private final int creditLimitMultiplier = 4;
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
        CreditApplication creditApplication = new CreditApplication();
        CreditCondition creditCondition = new CreditCondition();
        CreditApplication result = creditCondition.checkCreditCondition(creditApplication, creditApplicationCreateRequest);
        this.creditApplicationRepository.save(result);
        return new SuccessDataResult<>(new CreditApplicationResponse(result),"Credit application is added.");
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
