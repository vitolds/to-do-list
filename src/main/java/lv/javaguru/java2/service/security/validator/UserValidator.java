package lv.javaguru.java2.service.security.validator;

import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/15/2016.
 */
public interface UserValidator {
    enum Type {
        REGISTER, UPDATE
    }

    ValidatorMessage validateUser(User user, Type type);
    ValidatorMessage validateUsername(String username, Type type);
    ValidatorMessage validateEmail(String email, Type type);
    ValidatorMessage validatePassword(String password);
}
