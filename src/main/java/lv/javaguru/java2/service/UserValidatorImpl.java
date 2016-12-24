package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vitolds on 11/15/2016.
 */
@Component
public class UserValidatorImpl implements UserValidator {

    @Autowired
    UserDAO userDAO;

    private byte usernameLength = 16;
    private int emailLength = 255;

    @Override
    public ValidatorMessage validateUsername(String username) {

        if (username.length() > usernameLength)  {
            return new ValidatorMessage(false, "Username must match the length (" + usernameLength + ")");
        }
        if (userDAO.userNameExists(username)) {
            return new ValidatorMessage(false, "Account with that username already exists");
        }
        return new ValidatorMessage(true);
    }

    @Override
    public ValidatorMessage validateEmail(String email) {

        if (email.length() > emailLength) {
            return new ValidatorMessage(false, "Email must match the length (" + emailLength + ")");
        }
        if (userDAO.emailExists(email)) {
            return new ValidatorMessage(false, "Account with that email already exists");
        }
        if (!valEmailSynt(email)) {
            return new ValidatorMessage(false, "Invalid email");
        }
        return new ValidatorMessage(true);
    }

    @Override
    public ValidatorMessage validatePassword(String password) {
        if (password.length() < 8) {
            return new ValidatorMessage(false, "Password should be at least 8 characters long");
        } else {
            return new ValidatorMessage(true);
        }
    }

    @Override
    public ValidatorMessage validateUser(User user) {
        ValidatorMessage validatorMessage;

        // Username validation
        validatorMessage = validateUsername(user.getUserName());
        if (!validatorMessage.isSuccess()) return validatorMessage;

        // Email validation
        validatorMessage = validateEmail(user.getEmail());
        if (!validatorMessage.isSuccess()) return validatorMessage;

        // Password validation
        validatorMessage = validatePassword(user.getPassword());
        if (!validatorMessage.isSuccess()) return validatorMessage;

        return new ValidatorMessage(true);
    }

    // Method used to hash a password with the specified algorithm
    @Override
    public String getHashedPassword(String passW) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-1");
        passwordEncryptor.setPlainDigest(true);
        return passwordEncryptor.encryptPassword(passW);

        // Improve the implementation by adding the salt to the hash
    }

    private boolean valEmailSynt(String email) {
        String[] substrs = email.split("@");
        if (substrs.length != 2) return false;
        else return true;

        // Upgrade
    }
}
