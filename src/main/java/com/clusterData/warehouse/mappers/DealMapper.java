package com.clusterData.warehouse.mappers;


import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.models.Deal;
import org.springframework.stereotype.Component;

@Component
public class DealMapper {
    public Deal toEntity(DealDTO dealDTO) {
        Deal deal = new Deal();
        deal.setDealUniqueId(dealDTO.getDealUniqueId());
        deal.setFromCurrencyIsoCode(dealDTO.getFromCurrencyIsoCode());
        deal.setToCurrencyIsoCode(dealDTO.getToCurrencyIsoCode());
        deal.setDealTimestamp(dealDTO.getDealTimestamp());
        deal.setDealAmount(dealDTO.getDealAmount());
        return deal;
    }

    public DealDTO toDTO(Deal deal) {
        DealDTO dealDTO = new DealDTO();
        dealDTO.setDealUniqueId(deal.getDealUniqueId());
        dealDTO.setFromCurrencyIsoCode(deal.getFromCurrencyIsoCode());
        dealDTO.setToCurrencyIsoCode(deal.getToCurrencyIsoCode());
        dealDTO.setDealTimestamp(deal.getDealTimestamp());
        dealDTO.setDealAmount(deal.getDealAmount());
        return dealDTO;
    }
}