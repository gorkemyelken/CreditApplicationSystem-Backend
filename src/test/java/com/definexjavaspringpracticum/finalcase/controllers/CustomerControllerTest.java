package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerUpdateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.services.CustomerService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    private CustomerController customerController;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = Mockito.mock(CustomerService.class);

        customerController = new CustomerController(customerService);
    }

    @Test
    public void testGetAllCustomers() {
        List<CustomerResponse> customerResponses = Collections.singletonList(new CustomerResponse());
        DataResult<List<CustomerResponse>> dataResult = new DataResult<>(customerResponses, true);
        Mockito.when(customerService.getAllCustomers()).thenReturn(dataResult);

        ResponseEntity<DataResult<List<CustomerResponse>>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateCustomer() {
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        CustomerResponse customerResponse = new CustomerResponse();
        DataResult<CustomerResponse> dataResult = new DataResult<>(customerResponse, true);
        Mockito.when(customerService.createCustomer(customerCreateRequest)).thenReturn(dataResult);

        ResponseEntity<DataResult<CustomerResponse>> response = customerController.createCustomer(customerCreateRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
        CustomerResponse customerResponse = new CustomerResponse();
        DataResult<CustomerResponse> dataResult = new DataResult<>(customerResponse, true);
        Mockito.when(customerService.updateCustomer(customerId,customerUpdateRequest)).thenReturn(dataResult);

        ResponseEntity<DataResult<CustomerResponse>> response = customerController.updateCustomer(customerId, customerUpdateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Long customerId = 1L;
        CustomerResponse customerResponse = new CustomerResponse();
        DataResult<CustomerResponse> dataResult = new DataResult<>(customerResponse, true);
        Mockito.when(customerService.deleteCustomer(customerId)).thenReturn(dataResult);

        ResponseEntity<DataResult<CustomerResponse>> response = customerController.deleteCustomer(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByCustomerId() {
        Long customerId = 1L;
        CustomerResponse customerResponse = new CustomerResponse();
        DataResult<CustomerResponse> dataResult = new DataResult<>(customerResponse, true);
        Mockito.when(customerService.findByCustomerId(customerId)).thenReturn(dataResult);

        ResponseEntity<DataResult<CustomerResponse>> response = customerController.findByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByCustomerIdentityNumber() {
        String identityNumber = "12345678910";
        CustomerResponse customerResponse = new CustomerResponse();
        DataResult<CustomerResponse> dataResult = new DataResult<>(customerResponse, true);
        Mockito.when(customerService.findByCustomerIdentityNumber(identityNumber)).thenReturn(dataResult);

        ResponseEntity<DataResult<CustomerResponse>> response = customerController.findByCustomerIdentityNumber(identityNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(customerService, Mockito.times(1)).findByCustomerIdentityNumber(identityNumber);
    }
}