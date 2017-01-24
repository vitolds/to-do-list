package lv.javaguru.java2.service.security;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.database.springJPA.UserRoleRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserRole;
import lv.javaguru.java2.service.security.validator.UserValidator;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vitolds on 11/14/2016.
 */

@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public ValidatorMessage registerUser(User user) {

        ValidatorMessage validatorMessage = userValidator.validateUser(user);
        if (!validatorMessage.isSuccess()) return validatorMessage;
            else {
                try {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                    user = userRepository.save(user);
                    UserRole userRole = new UserRole();
                    userRole.setUserId(user.getUserId());
                    userRole.setRole("ROLE_USER");
                    userRoleRepository.save(userRole);
                } catch (Exception e) {
                    return new ValidatorMessage(false, "?error");
                }
        }
        return new ValidatorMessage(true, null);
    }
}
