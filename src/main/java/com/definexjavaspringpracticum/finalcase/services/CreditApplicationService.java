package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.entities.CreditApplication;
import com.definexjavaspringpracticum.finalcase.entities.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.services.constants.CreditCondition;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorDataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    public DataResult<List<CreditApplicationResponse>> getAllCreditApplications() {
        log.debug("[{}][getAllCreditApplications] -> request: {}", this.getClass().getSimpleName(), "Get all credit applications.");
        List<CreditApplication> creditApplications = this.creditApplicationRepository.findAll();
        List<CreditApplicationResponse> result = creditApplications.stream().map(creditApplication -> this.modelMapperService.forDto().map(creditApplication,CreditApplicationResponse.class)).collect(Collectors.toList());
        log.debug("[{}][getAllCreditApplications] -> response: {}", this.getClass().getSimpleName(), result.isEmpty() ? "Credit applications are not listed." : "Credit applications are listed.");
        return new SuccessDataResult<>(result,"Credit applications are listed.");
    }

    public DataResult<CreditApplicationResponse> createCreditApplication(CreditApplicationCreateRequest creditApplicationCreateRequest){
        log.debug("[{}][createCreditApplication] -> request: {}", this.getClass().getSimpleName(), creditApplicationCreateRequest);
        if(!checkIfCreditApplicationCustomerExist(creditApplicationCreateRequest.getCustomer())){
            log.debug("[{}][createCreditApplication] -> response: {}", this.getClass().getSimpleName(), "Customer is not found.");
            return new ErrorDataResult<>("Customer is not found.");
        }
        CreditApplication creditApplication = new CreditApplication();
        CreditCondition creditCondition = new CreditCondition();
        CreditApplication result = creditCondition.checkCreditCondition(creditApplication, creditApplicationCreateRequest);
        this.creditApplicationRepository.save(result);
        log.debug("[{}][createCreditApplication] -> response: {}", this.getClass().getSimpleName(), result);
        return new SuccessDataResult<>(new CreditApplicationResponse(result),"Credit application is added.");
    }

    public DataResult<CreditApplicationResponse> deleteCreditApplication(Long creditApplicationId){
        log.debug("[{}][deleteCreditApplication] -> request: {}", this.getClass().getSimpleName(), "Delete credit application.");
        if(!checkIfCreditApplicationIdExist(creditApplicationId)){
            log.debug("[{}][deleteCreditApplication] -> response: {}", this.getClass().getSimpleName(), "Credit application id is not found.");
            return new ErrorDataResult<>("Credit application id is not found.");
        }
        else{
            CreditApplication creditApplication = this.creditApplicationRepository.findByCreditApplicationId(creditApplicationId);
            this.creditApplicationRepository.deleteById(creditApplicationId);
            log.debug("[{}][deleteCreditApplication] -> response: {}", this.getClass().getSimpleName(), creditApplication);
            return new SuccessDataResult<>(new CreditApplicationResponse(creditApplication),"Credit application is deleted.");
        }
    }

    public DataResult<List<Object>> find(String identityNumber, Date birthDate){
        log.debug("[{}][find] -> request: {}", this.getClass().getSimpleName(), "Find by identity number and birth date.");
        return new SuccessDataResult<>(this.creditApplicationRepository.findByIdentityNumberAndBirthDate(identityNumber,birthDate),"Data listed.");
    }

    public DataResult<CreditApplicationResponse> findByCreditApplicationId(Long creditApplicationId){
        log.debug("[{}][findByCreditApplicationId] -> request: {}", this.getClass().getSimpleName(), "Find by credit application id.");
        CreditApplication creditApplication = this.creditApplicationRepository.findByCreditApplicationId(creditApplicationId);
        CreditApplicationResponse result = this.modelMapperService.forDto().map(creditApplication, CreditApplicationResponse.class);
        log.debug("[{}][findByCreditApplicationId] -> response: {}", this.getClass().getSimpleName(), result);
        return new SuccessDataResult<>(result, "Credit application is listed.");
    }

    private boolean checkIfCreditApplicationIdExist(Long creditApplicationId) {
        return this.creditApplicationRepository.existsByCreditApplicationId(creditApplicationId);
    }

    private boolean checkIfCreditApplicationCustomerExist(Customer customer) {
        return this.customerRepository.existsByCustomerId(customer.getCustomerId());
    }
}
