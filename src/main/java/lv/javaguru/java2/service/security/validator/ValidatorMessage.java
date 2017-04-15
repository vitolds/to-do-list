package lv.javaguru.java2.service.security.validator;

/**
 * Created by Vitolds on 12/17/2016.
 */
public class ValidatorMessage {

    private boolean success;
    private String message;

    public ValidatorMessage(boolean success) {
        this.success = success;
    }

    public ValidatorMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
