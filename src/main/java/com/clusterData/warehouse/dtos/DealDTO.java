package com.clusterData.warehouse.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DealDTO {
    @NotNull(message = "Deal Unique Id is required")
    private String dealUniqueId;

    @NotNull(message = "From Currency ISO Code is required")
    @Size(min = 3, max = 3, message = "From Currency ISO Code must be 3 characters long")
    private String fromCurrencyIsoCode;

    @NotNull(message = "To Currency ISO Code is required")
    @Size(min = 3, max = 3, message = "To Currency ISO Code must be 3 characters long")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal Timestamp is required")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deal Amount must be greater than zero")
    private BigDecimal dealAmount;
}