package com.clusterData.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateDealException extends RuntimeException {
    public DuplicateDealException(String message) {
        super(message);
    }
}
