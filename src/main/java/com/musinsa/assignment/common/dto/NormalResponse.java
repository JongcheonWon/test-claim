package com.musinsa.assignment.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NormalResponse {
    private final String code;
    private final String message;
}
