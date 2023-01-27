package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.services.constants.CreditCondition;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorDataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
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

    public DataResult<List<Object>> find(String identityNumber, Date birthDate){
        return new SuccessDataResult<>(this.creditApplicationRepository.findByIdentityNumberAndBirthDate(identityNumber,birthDate),"Data listed.");
    }

    public DataResult<CreditApplicationResponse> findByCreditApplicationId(Long creditApplicationId){
        CreditApplication creditApplication = this.creditApplicationRepository.findByCreditApplicationId(creditApplicationId);
        CreditApplicationResponse result = this.modelMapperService.forDto().map(creditApplication, CreditApplicationResponse.class);
        return new SuccessDataResult<>(result, "Credit application is listed.");
    }

    private boolean checkIfCreditApplicationIdExist(Long creditApplicationId) {
        return this.creditApplicationRepository.existsByCreditApplicationId(creditApplicationId);
    }

    private boolean checkIfCreditApplicationCustomerExist(Customer customer) {
        return this.customerRepository.existsByCustomerId(customer.getCustomerId());
    }
}
