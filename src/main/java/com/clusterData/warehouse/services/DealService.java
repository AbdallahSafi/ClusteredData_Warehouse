package com.clusterData.warehouse.services;

import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.exceptions.DuplicateDealException;
import com.clusterData.warehouse.mappers.DealMapper;
import com.clusterData.warehouse.models.Deal;
import com.clusterData.warehouse.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealMapper dealMapper;

    public DealDTO saveDeal(DealDTO dealDTO) {
        if (dealRepository.existsById(dealDTO.getDealUniqueId())) {
            logger.warn("Duplicate deal attempt with ID: {}", dealDTO.getDealUniqueId());
            throw new DuplicateDealException("Deal with this ID already exists");
        }
        Deal deal = dealMapper.toEntity(dealDTO);
        Deal savedDeal = dealRepository.save(deal);
        logger.info("Deal saved with ID: {}", savedDeal.getDealUniqueId());
        return dealMapper.toDTO(savedDeal);
    }
}