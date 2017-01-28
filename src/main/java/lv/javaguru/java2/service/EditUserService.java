package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;

/**
 * Created by vitol on 28/01/2017.
 */
public interface EditUserService {
    ValidatorMessage updateUser(User user);
}
