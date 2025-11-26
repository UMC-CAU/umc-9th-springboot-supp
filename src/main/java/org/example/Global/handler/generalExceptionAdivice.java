package org.example.Global.handler;

import org.example.Global.exception.generalException;
import org.example.Global.response.ApiResponse;
import org.example.Global.response.code.BaseErrorCode;
import org.example.Global.response.code.GeneralErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class generalExceptionAdivice {
    @ExceptionHandler(generalException.class)
    public ResponseEntity<ApiResponse<Void>> handlerException(generalException e) {
        return ResponseEntity.status(e.getCode().getStatus()).body(ApiResponse.onFailure(e.getCode(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ee) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ee.getMessage()
                        )
                );
    }
}