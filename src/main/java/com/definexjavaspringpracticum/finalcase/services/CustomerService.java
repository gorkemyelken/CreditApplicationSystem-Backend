package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.modals.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerUpdateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.utilities.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public CustomerResponse createCustomer(CustomerCreateRequest customerCreateRequest){
        Customer customer = this.customerRepository.save(new Customer(
                customerCreateRequest.getIdentityNumber(),
                customerCreateRequest.getFirstName(),
                customerCreateRequest.getLastName(),
                customerCreateRequest.getMonthlyIncome(),
                customerCreateRequest.getPhoneNumber(),
                customerCreateRequest.getBirthDate()));

        return new CustomerResponse(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest customerUpdateRequest){
        Optional<Customer> customer = this.customerRepository.findById(id);
        if(customer.isPresent()){
            Customer customerToUpdate = customer.get();
            customerToUpdate.setFirstName(customerUpdateRequest.getFirstName());
            customerToUpdate.setLastName(customerUpdateRequest.getLastName());
            customerToUpdate.setBirthDate(customerUpdateRequest.getBirthDate());
            customerToUpdate.setMonthlyIncome(customerUpdateRequest.getMonthlyIncome());
            customerToUpdate.setIdentityNumber(customerUpdateRequest.getIdentityNumber());
            customerToUpdate.setPhoneNumber(customerUpdateRequest.getPhoneNumber());
            customerRepository.save(customerToUpdate);
            return new CustomerResponse(customerToUpdate);
        }
        return null;
    }
}
