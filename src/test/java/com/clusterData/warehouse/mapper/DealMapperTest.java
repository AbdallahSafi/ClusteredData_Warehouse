package com.clusterData.warehouse.mapper;


import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.mappers.DealMapper;
import com.clusterData.warehouse.models.Deal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealMapperTest {

    private DealMapper dealMapper;
    private DealDTO dealDTO;
    private Deal deal;

    @BeforeEach
    void setUp() {
        dealMapper = new DealMapper();

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
    void testToEntity() {
        Deal mappedDeal = dealMapper.toEntity(dealDTO);
        assertEquals(dealDTO.getDealUniqueId(), mappedDeal.getDealUniqueId());
        assertEquals(dealDTO.getFromCurrencyIsoCode(), mappedDeal.getFromCurrencyIsoCode());
        assertEquals(dealDTO.getToCurrencyIsoCode(), mappedDeal.getToCurrencyIsoCode());
        assertEquals(dealDTO.getDealTimestamp(), mappedDeal.getDealTimestamp());
        assertEquals(dealDTO.getDealAmount(), mappedDeal.getDealAmount());
    }

    @Test
    void testToDTO() {
        DealDTO mappedDealDTO = dealMapper.toDTO(deal);
        assertEquals(deal.getDealUniqueId(), mappedDealDTO.getDealUniqueId());
        assertEquals(deal.getFromCurrencyIsoCode(), mappedDealDTO.getFromCurrencyIsoCode());
        assertEquals(deal.getToCurrencyIsoCode(), mappedDealDTO.getToCurrencyIsoCode());
        assertEquals(deal.getDealTimestamp(), mappedDealDTO.getDealTimestamp());
        assertEquals(deal.getDealAmount(), mappedDealDTO.getDealAmount());
    }
}