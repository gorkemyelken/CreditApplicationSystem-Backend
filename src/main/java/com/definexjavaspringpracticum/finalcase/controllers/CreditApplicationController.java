package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.services.CreditApplicationService;
import com.definexjavaspringpracticum.finalcase.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/creditapplications")
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @Autowired
    public CreditApplicationController(CreditApplicationService creditApplicationService) {
        this.creditApplicationService = creditApplicationService;
    }

    @GetMapping
    public ResponseEntity<Result<List<CreditApplicationResponse>>> getAllCreditApplications(){
        return new ResponseEntity<>(this.creditApplicationService.getAllCreditApplications(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result<CreditApplicationResponse>> createCreditApplication(@RequestBody CreditApplicationCreateRequest creditApplicationCreateRequest){
        return new ResponseEntity<>(this.creditApplicationService.createCreditApplication(creditApplicationCreateRequest),HttpStatus.CREATED );
    }

    @DeleteMapping("/{creditApplicationId}")
    public ResponseEntity<Result<CreditApplicationResponse>> delete(@PathVariable Long creditApplicationId){
        return new ResponseEntity<>(this.creditApplicationService.delete(creditApplicationId),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Result<Object>> find(@RequestParam String identityNumber, @RequestParam Date birthDate){
        return new ResponseEntity<>(this.creditApplicationService.find(identityNumber,birthDate),HttpStatus.OK);
    }
}
