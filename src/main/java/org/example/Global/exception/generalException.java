package org.example.Global.exception;
import org.example.Global.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class generalException extends RuntimeException {
    private final BaseErrorCode code;
}
