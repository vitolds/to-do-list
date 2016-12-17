package lv.javaguru.java2.service;

/**
 * Created by Vitolds on 12/17/2016.
 */
public class ValidatorMessage {

    private boolean success;
    private String message;

    ValidatorMessage(boolean success) {
        this.success = success;
    }

    ValidatorMessage(boolean success, String message) {
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
