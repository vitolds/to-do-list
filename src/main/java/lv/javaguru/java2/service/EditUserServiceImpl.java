package lv.javaguru.java2.service;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.validator.UserValidator;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by vitol on 28/01/2017.
 */
@Component
public class EditUserServiceImpl implements EditUserService {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ValidatorMessage updateUser(User user) {
        ValidatorMessage validatorMessage = userValidator.validateUser(user, UserValidator.Type.UPDATE);

        if (!validatorMessage.isSuccess()) return validatorMessage;
        else {
            try {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            } catch (Exception e) {
                return new ValidatorMessage(false, "?error");
            }
        }
        return new ValidatorMessage(true, null);
    }
}
