package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/14/2016.
 */
public interface RegisterService {

    String SUCCESS_MESSAGE = "Your account has been created successfully";
    String getMessage();
    boolean registerUser(User user, String password);
}
