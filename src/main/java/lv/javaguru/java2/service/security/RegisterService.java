package lv.javaguru.java2.service.security;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;

/**
 * Created by Vitolds on 11/14/2016.
 */
public interface RegisterService {

    ValidatorMessage registerUser(User user);
}
