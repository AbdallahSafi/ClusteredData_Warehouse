package com.clusterData.warehouse.dto;

import com.clusterData.warehouse.dtos.DealDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealDTOTest {

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
    void testGettersAndSetters() {
        assertEquals("123", dealDTO.getDealUniqueId());
        assertEquals("USD", dealDTO.getFromCurrencyIsoCode());
        assertEquals("EUR", dealDTO.getToCurrencyIsoCode());
        assertEquals(LocalDateTime.now().getDayOfYear(), dealDTO.getDealTimestamp().getDayOfYear());
        assertEquals(BigDecimal.valueOf(1000), dealDTO.getDealAmount());
    }
}