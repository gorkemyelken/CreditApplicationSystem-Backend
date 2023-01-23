package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }


}
