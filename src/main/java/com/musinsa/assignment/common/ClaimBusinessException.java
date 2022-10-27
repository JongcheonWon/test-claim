package com.musinsa.assignment.common;

import lombok.Getter;

@Getter
public class ClaimBusinessException extends RuntimeException {

    private final ResponseConstants responseConstants;

    public ClaimBusinessException(ResponseConstants responseConstants) {
        super(responseConstants.getMessage());
        this.responseConstants = responseConstants;
    }
}
