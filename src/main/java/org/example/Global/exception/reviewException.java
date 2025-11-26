package org.example.Global.exception;
import org.example.Global.response.code.BaseErrorCode;

public class reviewException extends generalException {
    public reviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
