package com.clusterData.warehouse.service;



import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.exceptions.DuplicateDealException;
import com.clusterData.warehouse.mappers.DealMapper;
import com.clusterData.warehouse.models.Deal;
import com.clusterData.warehouse.repositories.DealRepository;
import com.clusterData.warehouse.services.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DealServiceTest {

    @Mock
    private DealRepository dealRepository;

    @Mock
    private DealMapper dealMapper;

    @InjectMocks
    private DealService dealService;

    private DealDTO dealDTO;
    private Deal deal;

    @BeforeEach
    void setUp() {
        dealDTO = new DealDTO();
        dealDTO.setDealUniqueId("123");
        dealDTO.setFromCurrencyIsoCode("USD");
        dealDTO.setToCurrencyIsoCode("EUR");
        dealDTO.setDealTimestamp(LocalDateTime.now());
        dealDTO.setDealAmount(BigDecimal.valueOf(1000));

        deal = new Deal();
        deal.setDealUniqueId("123");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(BigDecimal.valueOf(1000));
    }

    @Test
    void testSaveDeal_Success() {
        when(dealRepository.existsById(dealDTO.getDealUniqueId())).thenReturn(false);
        when(dealMapper.toEntity(dealDTO)).thenReturn(deal);
        when(dealRepository.save(deal)).thenReturn(deal);
        when(dealMapper.toDTO(deal)).thenReturn(dealDTO);

        DealDTO savedDeal = dealService.saveDeal(dealDTO);

        assertNotNull(savedDeal);
        assertEquals(dealDTO.getDealUniqueId(), savedDeal.getDealUniqueId());
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    void testSaveDeal_DuplicateDealException() {
        when(dealRepository.existsById(dealDTO.getDealUniqueId())).thenReturn(true);

        assertThrows(DuplicateDealException.class, () -> {
            dealService.saveDeal(dealDTO);
        });

        verify(dealRepository, never()).save(any(Deal.class));
    }

    @Test
    void testSaveDeal_MapperError() {
        when(dealRepository.existsById(dealDTO.getDealUniqueId())).thenReturn(false);
        when(dealMapper.toEntity(dealDTO)).thenThrow(new RuntimeException("Mapper error"));

        assertThrows(RuntimeException.class, () -> {
            dealService.saveDeal(dealDTO);
        });

        verify(dealRepository, never()).save(any(Deal.class));
    }

    @Test
    void testSaveDeal_NullDealDTO() {
        assertThrows(NullPointerException.class, () -> {
            dealService.saveDeal(null);
        });

        verify(dealRepository, never()).save(any(Deal.class));
    }
}