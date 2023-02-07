package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.entities.CreditApplication;
import com.definexjavaspringpracticum.finalcase.entities.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.utilities.mapping.ModelMapperService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CreditApplicationServiceTest {

    private CreditApplicationRepository creditApplicationRepository;
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    private CreditApplicationService creditApplicationService;

    @Before
    public void setUp() throws Exception {
        creditApplicationRepository = Mockito.mock(CreditApplicationRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        modelMapperService = Mockito.mock(ModelMapperService.class);

        creditApplicationService = new CreditApplicationService(creditApplicationRepository,customerRepository,modelMapperService);
    }

    @Test
    public void testCreateCreditApplication() {
        Customer customer = new Customer("12345678901", "TestFirstName", "TestLastName", 5000.0, "5555555555", Date.valueOf("1998-09-06"), 650);
        CreditApplicationCreateRequest creditApplicationCreateRequest = new CreditApplicationCreateRequest(customer);

        Mockito.when(customerRepository.existsByCustomerId(customer.getCustomerId())).thenReturn(true);

        CreditApplication creditApplication = new CreditApplication(1L,"Approved.",10000.0,Date.valueOf("2023-02-07"),customer,5000.0,650);

        Mockito.when(creditApplicationRepository.save(creditApplication)).thenReturn(creditApplication);

        DataResult<CreditApplicationResponse> result = creditApplicationService.createCreditApplication(creditApplicationCreateRequest);

        assertEquals("Credit application is added.", result.getMessage());
        assertNotNull(result.getData());
    }

    @Test
    public void testDeleteCreditApplication() {
        Customer customer = new Customer("12345678901", "TestFirstName", "TestLastName", 5000.0, "5555555555", Date.valueOf("1998-09-06"), 650);
        CreditApplication creditApplication = new CreditApplication(1L,"Approved.",10000.0,Date.valueOf("2023-02-07"),customer,5000.0,650);
        Long creditApplicationId = creditApplication.getCreditApplicationId();

        Mockito.when(creditApplicationRepository.existsByCreditApplicationId(creditApplicationId)).thenReturn(true);
        Mockito.when(creditApplicationRepository.findByCreditApplicationId(creditApplicationId)).thenReturn(creditApplication);

        DataResult<CreditApplicationResponse> result = creditApplicationService.delete(creditApplicationId);
        assertEquals("Credit application is deleted.", result.getMessage());
    }
}