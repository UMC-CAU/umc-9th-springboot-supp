package org.example.Global.response.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
