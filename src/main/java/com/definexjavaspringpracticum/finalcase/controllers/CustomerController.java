package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest){
        return new ResponseEntity<>(this.customerService.createCustomer(customerCreateRequest),HttpStatus.CREATED );
    }
}
