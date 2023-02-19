package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.entities.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerUpdateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.ErrorDataResult;
import com.definexjavaspringpracticum.finalcase.utilities.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    public DataResult<List<CustomerResponse>> getAllCustomers() {
        log.debug("[{}][getAllCustomers] -> request: {}", this.getClass().getSimpleName(), "Get all customers.");
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerResponse> result = customers.stream().map(customer -> this.modelMapperService.forDto().map(customer,CustomerResponse.class)).collect(Collectors.toList());
        log.debug("[{}][getAllCustomers] -> response: {}", this.getClass().getSimpleName(), result.isEmpty() ? "Customers are not listed." : "Customers are listed.");
        return new SuccessDataResult<>(result,"Customers are listed.");
    }

    public DataResult<CustomerResponse> createCustomer(CustomerCreateRequest customerCreateRequest){
        log.debug("[{}][createCustomer] -> request: {}", this.getClass().getSimpleName(), customerCreateRequest);
        if(checkIfIdentityNumberExists(customerCreateRequest.getIdentityNumber())){
            log.debug("[{}][createCustomer] -> response: {}", this.getClass().getSimpleName(), "Identity number is already exist.");
            return new ErrorDataResult<>("Identity number is already exist.");
        }
        else{
            Random random = new Random();
            Customer customer = new Customer(
                    customerCreateRequest.getIdentityNumber(),
                    customerCreateRequest.getFirstName(),
                    customerCreateRequest.getLastName(),
                    customerCreateRequest.getMonthlyIncome(),
                    customerCreateRequest.getPhoneNumber(),
                    customerCreateRequest.getBirthDate(),
                    random.nextInt(1001));
            this.customerRepository.save(customer);
            log.debug("[{}][createCustomer] -> response: {}", this.getClass().getSimpleName(), customer);
            return new SuccessDataResult<>(new CustomerResponse(customer),"Customer is added.");
        }
    }

    public DataResult<CustomerResponse> updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest){
        log.debug("[{}][updateCustomer] -> request: {}", this.getClass().getSimpleName(), customerUpdateRequest);
        if(!checkIfCustomerIdExists(customerId)){
            log.debug("[{}][updateCustomer] -> response: {}", this.getClass().getSimpleName(), "Customer id is not found.");
            return new ErrorDataResult<>("Customer id is not found.");
        }
        else{
            Customer customer = this.customerRepository.findByCustomerId(customerId);
            customer.setFirstName(customerUpdateRequest.getFirstName());
            customer.setLastName(customerUpdateRequest.getLastName());
            customer.setBirthDate(customerUpdateRequest.getBirthDate());
            customer.setMonthlyIncome(customerUpdateRequest.getMonthlyIncome());
            customer.setIdentityNumber(customerUpdateRequest.getIdentityNumber());
            customer.setPhoneNumber(customerUpdateRequest.getPhoneNumber());
            customer.setCreditScore(customer.getCreditScore());
            customerRepository.save(customer);
            log.debug("[{}][updateCustomer] -> response: {}", this.getClass().getSimpleName(), customer);
            return new SuccessDataResult<>(new CustomerResponse(customer),"Customer is updated.");
        }
    }



    public DataResult<CustomerResponse> deleteCustomer(Long customerId){
        log.debug("[{}][deleteCustomer] -> request: {}", this.getClass().getSimpleName(), "Delete customer.");
        if(!checkIfCustomerIdExists(customerId)){
            log.debug("[{}][deleteCustomer] -> response: {}", this.getClass().getSimpleName(), "Customer id is not found.");
            return new ErrorDataResult<>("Customer id is not found.");
        }
        else{
            Customer customer = this.customerRepository.findByCustomerId(customerId);
            this.customerRepository.deleteById(customerId);
            log.debug("[{}][deleteCustomer] -> response: {}", this.getClass().getSimpleName(), customer);
            return new SuccessDataResult<>(new CustomerResponse(customer),"Customer is deleted.");
        }
    }

    public DataResult<CustomerResponse> findByCustomerId(Long customerId){
        log.debug("[{}][findByCustomerId] -> request: {}", this.getClass().getSimpleName(), "Find by customer id.");
        Customer customer = this.customerRepository.findByCustomerId(customerId);
        CustomerResponse result = this.modelMapperService.forDto().map(customer, CustomerResponse.class);
        log.debug("[{}][findByCustomerId] -> response: {}", this.getClass().getSimpleName(), result);
        return new SuccessDataResult<>(result, "Customer is listed.");
    }

    public DataResult<CustomerResponse> findByCustomerIdentityNumber(String identityNumber){
        log.debug("[{}][findByCustomerIdentityNumber] -> request: {}", this.getClass().getSimpleName(), "Find by customer identity number.");
        Customer customer = this.customerRepository.findByIdentityNumber(identityNumber);
        CustomerResponse result = this.modelMapperService.forDto().map(customer, CustomerResponse.class);
        log.debug("[{}][findByCustomerIdentityNumber] -> response: {}", this.getClass().getSimpleName(), result);
        return new SuccessDataResult<>(result, "Customer is listed.");
    }

    private boolean checkIfIdentityNumberExists(String identityNumber) {
        return this.customerRepository.existsByIdentityNumber(identityNumber);
    }

    private boolean checkIfCustomerIdExists(Long customerId) {
        return this.customerRepository.existsByCustomerId(customerId);
    }
}
