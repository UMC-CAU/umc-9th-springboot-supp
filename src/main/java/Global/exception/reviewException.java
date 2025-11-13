package Global.exception;
import Global.response.code.BaseErrorCode;
import Global.exception.generalException;

public class reviewException extends generalException {
    public reviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
