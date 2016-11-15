package lv.javaguru.java2.service;

/**
 * Created by Vitolds on 11/15/2016.
 */
public interface UserValidator {
    String getMessage();
    boolean validateUsername(String username);
    boolean validateEmail(String email);
}
