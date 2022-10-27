package com.musinsa.assignment.common.dto;


import com.musinsa.assignment.common.ResponseConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
public class CommonResponse<T> {

    private boolean success;
    private T data;
    private Error error;

    public static <T> CommonResponse<T> createNormalResponse(T t){
        CommonResponse<T> response = new CommonResponse<>();
        response.data = t;
        response.success = true;
        return response;
    }

    public static CommonResponse<Void> createErrorResponse(ResponseConstants responseConstants){
        return createErrorResponse(responseConstants.getCode(), responseConstants.getMessage());
    }

    public static CommonResponse<Void> createErrorResponse(String code, String message){
        CommonResponse<Void> response = new CommonResponse<>();
        response.success = false;
        response.error = new Error(code, message);
        return response;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Error {
        private final String errorCode;
        private final String errorMessage;
    }
}
