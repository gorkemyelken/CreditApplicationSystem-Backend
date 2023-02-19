package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerUpdateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.services.CustomerService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<DataResult<List<CustomerResponse>>> getAllCustomers(){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Get all customers.");
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResult<CustomerResponse>> createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), "Create customer.");
        return new ResponseEntity<>(this.customerService.createCustomer(customerCreateRequest),HttpStatus.CREATED );
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<DataResult<CustomerResponse>> updateCustomer(@PathVariable Long customerId,@RequestBody CustomerUpdateRequest customerUpdateRequest){
        return new ResponseEntity<>(this.customerService.updateCustomer(customerId,customerUpdateRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<DataResult<CustomerResponse>> deleteCustomer(@PathVariable Long customerId){
        return new ResponseEntity<>(this.customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<DataResult<CustomerResponse>> findByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<>(this.customerService.findByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/findbyidentitynumber")
    public ResponseEntity<DataResult<CustomerResponse>> findByCustomerIdentityNumber(@RequestParam String identityNumber){
        return new ResponseEntity<>(this.customerService.findByCustomerIdentityNumber(identityNumber), HttpStatus.OK);
    }
}
