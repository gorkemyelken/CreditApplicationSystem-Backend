package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.services.CreditApplicationService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/creditapplications")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @GetMapping
    public ResponseEntity<DataResult<List<CreditApplicationResponse>>> getAllCreditApplications(){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Get all credit applications.");
        return new ResponseEntity<>(this.creditApplicationService.getAllCreditApplications(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResult<CreditApplicationResponse>> createCreditApplication(@RequestBody CreditApplicationCreateRequest creditApplicationCreateRequest){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Create credit application.");
        return new ResponseEntity<>(this.creditApplicationService.createCreditApplication(creditApplicationCreateRequest),HttpStatus.CREATED );
    }

    @DeleteMapping("/{creditApplicationId}")
    public ResponseEntity<DataResult<CreditApplicationResponse>> deleteCreditApplication(@PathVariable Long creditApplicationId){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Delete credit application.");
        return new ResponseEntity<>(this.creditApplicationService.deleteCreditApplication(creditApplicationId),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<DataResult<List<Object>>> find(@RequestParam String identityNumber,Date birthDate){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Find by identity number and birth date.");
        return new ResponseEntity<>(this.creditApplicationService.find(identityNumber,birthDate),HttpStatus.OK);
    }

    @GetMapping("{creditApplicationId}")
    public ResponseEntity<DataResult<CreditApplicationResponse>> findByCreditApplicationId(@PathVariable Long creditApplicationId){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Find by credit application id.");
        return new ResponseEntity<>(this.creditApplicationService.findByCreditApplicationId(creditApplicationId), HttpStatus.OK);
    }
}
