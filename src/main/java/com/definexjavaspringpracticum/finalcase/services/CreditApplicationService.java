package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.CreditApplication;
import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {
    private Date date;
    private CreditApplicationRepository creditApplicationRepository;
    private ModelMapperService modelMapperService;

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
        CreditApplication creditApplication = this.creditApplicationRepository.save(new CreditApplication(
                this.date,
                creditApplicationCreateRequest.getConfirmationInformation(),
                creditApplicationCreateRequest.getLimit()));
        return new SuccessDataResult<>(new CreditApplicationResponse(creditApplication),"Credit application is added.");
    }
}
