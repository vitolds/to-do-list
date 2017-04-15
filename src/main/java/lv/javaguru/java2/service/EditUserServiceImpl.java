package lv.javaguru.java2.service;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.SecurityService;
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

    @Autowired
    SecurityService securityService;

    @Override
    public ValidatorMessage updateUser(User user) {

        User loggedInUser = userRepository.findByUsername(securityService.findLoggedInUsername());

        user = convertUser(loggedInUser, user);

        ValidatorMessage validatorMessage = userValidator.validateUser(user, UserValidator.Type.UPDATE);

        if (!validatorMessage.isSuccess()) return validatorMessage;
        else {
            try {
                userRepository.save(user);
            } catch (Exception e) {
                return new ValidatorMessage(false, "?error");
            }
        }
        return new ValidatorMessage(true, "?success");
    }

    private User convertUser(User loggedInUser, User user) {
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            user.setEmail(loggedInUser.getEmail());
        }

        if (user.getFirstName() == null || "".equals(user.getFirstName())) {
            user.setFirstName(loggedInUser.getFirstName());
        }

        if (user.getLastName() == null || "".equals(user.getLastName())) {
            user.setLastName(loggedInUser.getLastName());
        }

        if (user.getPassword() == null || "".equals(user.getPassword())) {
            user.setPassword(loggedInUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if (user.getUsername() == null || "".equals(user.getUsername())) {
            user.setUsername(loggedInUser.getUsername());
        }

        user.setCoins(loggedInUser.getCoins());
        user.setUserId(loggedInUser.getUserId());

        return user;
    }
}
