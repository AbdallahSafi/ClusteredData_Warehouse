package com.clusterData.warehouse.exception;

import com.clusterData.warehouse.exceptions.DuplicateDealException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DuplicateDealExceptionTest {

    @Test
    void testDuplicateDealException() {
        String errorMessage = "Deal with this ID already exists";
        DuplicateDealException exception = assertThrows(DuplicateDealException.class, () -> {
            throw new DuplicateDealException(errorMessage);
        });
        assertEquals(errorMessage, exception.getMessage());
    }
}