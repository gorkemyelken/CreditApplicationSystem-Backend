package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.entities.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerUpdateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private CustomerService customerService;

    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    @Before
    public void setUp() throws Exception {
        customerRepository = Mockito.mock(CustomerRepository.class);
        modelMapperService = Mockito.mock(ModelMapperService.class);

        customerService = new CustomerService(customerRepository, modelMapperService);
    }

    @Test
    public void testCreateCustomer(){
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(
                "123456789",
                "TestFirstName",
                "TestLastName",
                5000.0,
                "555-555-5555",
                Date.valueOf("1998-09-06"),
                700);

        Customer customer = new Customer(customerCreateRequest.getIdentityNumber(),
                customerCreateRequest.getFirstName(),
                customerCreateRequest.getLastName(),
                customerCreateRequest.getMonthlyIncome(),
                customerCreateRequest.getPhoneNumber(),
                customerCreateRequest.getBirthDate(),
                customerCreateRequest.getCreditScore());
        customer.setCustomerId(1L);

        Mockito.when(customerRepository.existsByIdentityNumber(customerCreateRequest.getIdentityNumber())).thenReturn(false);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        DataResult<CustomerResponse> result = customerService.createCustomer(customerCreateRequest);

        assertEquals("Customer is added.", result.getMessage());
        assertNotNull(result.getData());
    }

    @Test
    public void testUpdateCustomer() {
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest("12345678901", "TestFirstNameUpdated", "TestLastName", 5000.0, "5555555555", Date.valueOf("1998-09-06"), 650);
        Customer customer = new Customer("12345678901", "TestFirstName", "TestLastName", 5000.0, "5555555555", Date.valueOf("1998-09-06"), 650);
        customer.setCustomerId(1L);
        Long customerId = customer.getCustomerId();

        Mockito.when(customerRepository.existsByCustomerId(customerId)).thenReturn(true);
        Mockito.when(customerRepository.findByCustomerId(customerId)).thenReturn(customer);

        DataResult<CustomerResponse> result = customerService.updateCustomer(customerId, customerUpdateRequest);
        assertEquals("Customer is updated.", result.getMessage());
        assertNotNull(result.getData());
        assertEquals("TestFirstNameUpdated", result.getData().getFirstName());
    }

    @Test
    public void testDeleteCustomer(){
        Customer customer = new Customer("12345678901", "TestFirstName", "TestLastName", 5000.0, "5555555555", Date.valueOf("1998-09-06"), 650);
        customer.setCustomerId(1L);
        Long customerId = customer.getCustomerId();

        Mockito.when(customerRepository.existsByCustomerId(customerId)).thenReturn(true);
        Mockito.when(customerRepository.findByCustomerId(customerId)).thenReturn(customer);

        DataResult<CustomerResponse> result = customerService.deleteCustomer(customerId);
        assertEquals("Customer is deleted.", result.getMessage());
    }
}