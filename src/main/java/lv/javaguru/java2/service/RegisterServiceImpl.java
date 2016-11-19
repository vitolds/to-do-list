package lv.javaguru.java2.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/14/2016.
 */
public class RegisterServiceImpl implements RegisterService {

    UserDAO userDAO = new UserDAOImpl();
    UserValidator userValidator = new UserValidatorImpl();

    private String message = null;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean registerUser(User user, String password) {

        if (!userValidator.validateUsername(user.getUserName())) {
            message = userValidator.getMessage();
            return false;
        }
        if (!userValidator.validateEmail(user.getEmail())) {
            message = userValidator.getMessage();
            return false;
        }

        try {
            userDAO.create(user, password);
        } catch (DBException e) {
            message = "Something went wrong, try to check syntax";
            return false;
        }

        return true;
    }
}
