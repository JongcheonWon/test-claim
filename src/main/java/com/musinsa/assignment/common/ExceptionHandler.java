package com.musinsa.assignment.common;

import com.musinsa.assignment.common.dto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ClaimBusinessException.class)
    public ResponseEntity<CommonResponse<Void>> handleClaimBusinessException(ClaimBusinessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.createErrorResponse(e.getResponseConstants()));
    }

}
