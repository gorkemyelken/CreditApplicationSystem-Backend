package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.utilities.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    public CustomerService(CustomerRepository customerRepository, ModelMapperService modelMapperService) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerResponse> result = customers.stream().map(customer -> this.modelMapperService.forDto().map(customer,CustomerResponse.class)).collect(Collectors.toList());
        return result;
    }

}
