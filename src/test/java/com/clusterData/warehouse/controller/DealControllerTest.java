package com.clusterData.warehouse.controller;


import com.clusterData.warehouse.controllers.DealController;
import com.clusterData.warehouse.dtos.ApiResponse;
import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.exceptions.DuplicateDealException;
import com.clusterData.warehouse.services.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealControllerTest {

    @Mock
    private DealService dealService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private DealController dealController;

    private DealDTO dealDTO;

    @BeforeEach
    void setUp() {
        dealDTO = new DealDTO();
        dealDTO.setDealUniqueId("123");
        dealDTO.setFromCurrencyIsoCode("USD");
        dealDTO.setToCurrencyIsoCode("EUR");
        dealDTO.setDealTimestamp(LocalDateTime.now());
        dealDTO.setDealAmount(BigDecimal.valueOf(1000));
    }

    @Test
    void testCreateDeal_Success() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(dealService.saveDeal(dealDTO)).thenReturn(dealDTO);

        ResponseEntity<ApiResponse<DealDTO>> response = dealController.createDeal(dealDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dealDTO, response.getBody().getData());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Deal created successfully", response.getBody().getMessage());
        verify(dealService, times(1)).saveDeal(dealDTO);
    }

    @Test
    void testCreateDeal_ValidationErrors() {
        FieldError fieldError = new FieldError("dealDTO", "dealUniqueId", "Deal Unique Id is required");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        ResponseEntity<ApiResponse<DealDTO>> response = dealController.createDeal(dealDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody().getData());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Deal Unique Id is required", response.getBody().getMessage());
        verify(dealService, never()).saveDeal(any(DealDTO.class));
    }

    @Test
    void testCreateDeal_DuplicateDealException() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(dealService.saveDeal(dealDTO)).thenThrow(new DuplicateDealException("Deal with this ID already exists"));

        ResponseEntity<ApiResponse<DealDTO>> response = dealController.createDeal(dealDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody().getData());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Deal with this ID already exists", response.getBody().getMessage());
        verify(dealService, times(1)).saveDeal(dealDTO);
    }
}