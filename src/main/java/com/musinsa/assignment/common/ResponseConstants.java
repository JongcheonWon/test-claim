package com.musinsa.assignment.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseConstants {

    NORMAL("0000", "정상 처리되었습니다."),
    VALIDATION_ERROR_0100("0100", "잘못된 구매번호입니다."),
    VALIDATION_ERROR_0101("0101", "전체 취소된 구매 건 입니다."),
    VALIDATION_ERROR_0102("0102", "잘못된 상품번호입니다."),
    ;

    private final String code;
    private final String message;
}
