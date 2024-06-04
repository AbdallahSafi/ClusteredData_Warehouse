package com.clusterData.warehouse.controllers;

import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.exceptions.DuplicateDealException;
import com.clusterData.warehouse.models.Deal;
import com.clusterData.warehouse.services.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/deals")
public class DealController {
    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @PostMapping
    public ResponseEntity<?> createDeal(@Valid @RequestBody DealDTO dealDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors: {}", bindingResult.getAllErrors());
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            DealDTO savedDealDTO = dealService.saveDeal(dealDTO);
            logger.info("Deal created successfully with ID: {}", savedDealDTO.getDealUniqueId());
            return new ResponseEntity<>(savedDealDTO, HttpStatus.CREATED);
        } catch (DuplicateDealException e) {
            logger.warn("Duplicate deal exception: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
