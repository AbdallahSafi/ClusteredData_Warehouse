package com.clusterData.warehouse.controllers;

import com.clusterData.warehouse.dtos.ApiResponse;
import com.clusterData.warehouse.dtos.DealDTO;
import com.clusterData.warehouse.exceptions.DuplicateDealException;
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

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deals")
public class DealController {
    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @PostMapping
    public ResponseEntity<ApiResponse<DealDTO>> createDeal(@Valid @RequestBody DealDTO dealDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            logger.error("Validation errors: {}", errors);
            ApiResponse<DealDTO> response = new ApiResponse<>(false, errors, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            DealDTO savedDealDTO = dealService.saveDeal(dealDTO);
            logger.info("Deal created successfully with ID: {}", savedDealDTO.getDealUniqueId());
            ApiResponse<DealDTO> response = new ApiResponse<>(true, "Deal created successfully", savedDealDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DuplicateDealException e) {
            logger.warn("Duplicate deal exception: {}", e.getMessage());
            ApiResponse<DealDTO> response = new ApiResponse<>(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}