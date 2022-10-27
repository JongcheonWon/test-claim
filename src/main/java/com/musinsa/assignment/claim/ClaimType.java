package com.musinsa.assignment.claim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimType {
    EXCHANGE("X"),
    RETURN("R"),
    UNKNOWN("");

    private final String code;

    @JsonCreator
    public static ClaimType fromCode(String code) {
        for (ClaimType claimType : values()) {
            if (claimType.code.equals(code)) {
                return claimType;
            }
        }

        return UNKNOWN;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
