package com.definexjavaspringpracticum.finalcase.controllers;

import com.definexjavaspringpracticum.finalcase.requests.CreditApplicationCreateRequest;
import com.definexjavaspringpracticum.finalcase.requests.CustomerCreateRequest;
import com.definexjavaspringpracticum.finalcase.responses.CreditApplicationResponse;
import com.definexjavaspringpracticum.finalcase.responses.CustomerResponse;
import com.definexjavaspringpracticum.finalcase.services.CreditApplicationService;
import com.definexjavaspringpracticum.finalcase.utilities.results.DataResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CreditApplicationControllerTest {

    private CreditApplicationController creditApplicationController;

    private CreditApplicationService creditApplicationService;

    @Before
    public void setUp() throws Exception {
        creditApplicationService = Mockito.mock(CreditApplicationService.class);

        creditApplicationController = new CreditApplicationController(creditApplicationService);
    }

    @Test
    public void testGetAllCreditApplications() {
        List<CreditApplicationResponse> creditApplicationResponses = Collections.singletonList(new CreditApplicationResponse());
        DataResult<List<CreditApplicationResponse>> dataResult = new DataResult<>(creditApplicationResponses, true);
        Mockito.when(creditApplicationService.getAllCreditApplications()).thenReturn(dataResult);

        ResponseEntity<DataResult<List<CreditApplicationResponse>>> response = creditApplicationController.getAllCreditApplications();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateCreditApplication() {
        CreditApplicationCreateRequest creditApplicationCreateRequest = new CreditApplicationCreateRequest();
        CreditApplicationResponse creditApplicationResponse = new CreditApplicationResponse();
        DataResult<CreditApplicationResponse> dataResult = new DataResult<>(creditApplicationResponse, true);
        Mockito.when(creditApplicationService.createCreditApplication(creditApplicationCreateRequest)).thenReturn(dataResult);

        ResponseEntity<DataResult<CreditApplicationResponse>> response = creditApplicationController.createCreditApplication(creditApplicationCreateRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Long creditApplicationId = 1L;
        CreditApplicationResponse creditApplicationResponse = new CreditApplicationResponse();
        DataResult<CreditApplicationResponse> dataResult = new DataResult<>(creditApplicationResponse, true);
        Mockito.when(creditApplicationService.deleteCreditApplication(creditApplicationId)).thenReturn(dataResult);

        ResponseEntity<DataResult<CreditApplicationResponse>> response = creditApplicationController.deleteCreditApplication(creditApplicationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFind() {
        String identityNumber = "12345678910";
        Date birthDate = Date.valueOf("1998-09-06");

        List<Object> objectList = new ArrayList<>();
        DataResult<List<Object>> dataResult = new DataResult<>(objectList, true);
        Mockito.when(creditApplicationService.find(identityNumber, birthDate)).thenReturn(dataResult);

        ResponseEntity<DataResult<List<Object>>> response = creditApplicationController.find(identityNumber, birthDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByCreditApplicationId() {
        Long creditApplicationId = 1L;
        CreditApplicationResponse creditApplicationResponse = new CreditApplicationResponse();
        DataResult<CreditApplicationResponse> dataResult = new DataResult<>(creditApplicationResponse, true);
        Mockito.when(creditApplicationService.findByCreditApplicationId(creditApplicationId)).thenReturn(dataResult);

        ResponseEntity<DataResult<CreditApplicationResponse>> response = creditApplicationController.findByCreditApplicationId(creditApplicationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}