package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.services.constants.CreditCondition;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.Result;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CreditApplicationService(CreditApplicationRepository creditApplicationRepository, CustomerRepository customerRepository, ModelMapperService modelMapperService) {
        this.creditApplicationRepository = creditApplicationRepository;
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
    }

    public Result<List<CreditApplicationResponse>> getAllCreditApplications() {
        List<CreditApplication> creditApplications = this.creditApplicationRepository.findAll();
        List<CreditApplicationResponse> result = creditApplications.stream().map(creditApplication -> this.modelMapperService.forDto().map(creditApplication,CreditApplicationResponse.class)).collect(Collectors.toList());
        return new SuccessResult<>(result,"Credit applications are listed.");
    }

    public Result<CreditApplicationResponse> createCreditApplication(CreditApplicationCreateRequest creditApplicationCreateRequest){
        if(!checkIfCreditApplicationCustomerExist(creditApplicationCreateRequest.getCustomer())){
            return new ErrorResult<>("Customer is not found.");
        }
        CreditApplication creditApplication = new CreditApplication();
        CreditCondition creditCondition = new CreditCondition();
        CreditApplication result = creditCondition.checkCreditCondition(creditApplication, creditApplicationCreateRequest);
        this.creditApplicationRepository.save(result);
        return new SuccessResult<>(new CreditApplicationResponse(result),"Credit application is added.");
    }

    public Result<CreditApplicationResponse> delete(Long creditApplicationId){
        if(!checkIfCreditApplicationIdExist(creditApplicationId)){
            return new ErrorResult<>("Credit application id is not found.");
        }
        else{
            CreditApplication creditApplication = this.creditApplicationRepository.findByCreditApplicationId(creditApplicationId);
            this.creditApplicationRepository.deleteById(creditApplicationId);
            return new SuccessResult<>(new CreditApplicationResponse(creditApplication),"Credit application is deleted.");
        }
    }

    public Result<Object> find(String identityNumber, Date birthDate){
        return new SuccessResult<>(this.creditApplicationRepository.findByIdentityNumberAndBirthDate(identityNumber,birthDate),"Data listed.");
    }

    private boolean checkIfCreditApplicationIdExist(Long creditApplicationId) {
        return this.creditApplicationRepository.existsByCreditApplicationId(creditApplicationId);
    }

    private boolean checkIfCreditApplicationCustomerExist(Customer customer) {
        return this.customerRepository.existsByCustomerId(customer.getCustomerId());
    }
}
