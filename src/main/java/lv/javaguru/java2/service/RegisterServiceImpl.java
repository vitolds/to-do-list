package lv.javaguru.java2.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vitolds on 11/14/2016.
 */

@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    UserValidator userValidator;

    @Override
    @Transactional
    public ValidatorMessage registerUser(User user) {

        ValidatorMessage validatorMessage = userValidator.validateUser(user);

        if (!validatorMessage.isSuccess()) return validatorMessage;
            else {
                try {
                    user.setPassword(userValidator.getHashedPassword(user.getPassword()));
                    userDAO.create(user);
                } catch (DBException e) {
                    return new ValidatorMessage(false, "Something went wrong, try to check syntax");
                }
        }
        return new ValidatorMessage(true, SUCCESS_MESSAGE);
    }
}
