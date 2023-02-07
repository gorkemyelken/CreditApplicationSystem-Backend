package com.definexjavaspringpracticum.finalcase.services;

import com.definexjavaspringpracticum.finalcase.entities.CreditApplication;
import com.definexjavaspringpracticum.finalcase.entities.Customer;
import com.definexjavaspringpracticum.finalcase.repositories.CreditApplicationRepository;
import com.definexjavaspringpracticum.finalcase.repositories.CustomerRepository;
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
    public void createCreditApplication() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void find() {
    }

    @Test
    public void findByCreditApplicationId() {
    }
}