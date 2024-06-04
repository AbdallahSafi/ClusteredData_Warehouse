package com.clusterData.warehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Deal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Deal {
    @Id
    private String dealUniqueId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
