package Global.exception;
import Global.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class generalException extends RuntimeException {
    private final BaseErrorCode code;
}
