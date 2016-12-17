package lv.javaguru.java2.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public ValidatorMessage registerUser(User user, String password) {

        ValidatorMessage validatorMessage = userValidator.validateUser(user, password);

        if (!validatorMessage.isSuccess()) return validatorMessage;
            else {
                try {
                    userDAO.create(user, password);
                } catch (DBException e) {
                    return new ValidatorMessage(false, "Something went wrong, try to check syntax");
                }
        }
        return new ValidatorMessage(true, "<span style=\"color: green\">" + SUCCESS_MESSAGE + "</span>");
    }
}
