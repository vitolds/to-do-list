package lv.javaguru.java2.service.security.validator;

import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/15/2016.
 */
public interface UserValidator {
    ValidatorMessage validateUser(User user);
    ValidatorMessage validateUsername(String username);
    ValidatorMessage validateEmail(String email);
    ValidatorMessage validatePassword(String password);
}
