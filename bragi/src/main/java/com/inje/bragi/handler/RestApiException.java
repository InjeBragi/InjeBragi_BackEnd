package com.inje.bragi.handler;

import com.inje.bragi.entity.enumSet.ErrorType;
import lombok.Getter;


@Getter
public class RestApiException extends RuntimeException {
    private final ErrorType errorType;

    public RestApiException(ErrorType errorType) {
        this.errorType = errorType;
    }
}
