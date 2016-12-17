package lv.javaguru.java2.service;

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
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User authenticate (String login, String password) {
        User user = null;
        try {
            user = userDAO.getByLogin(login, password);
        } catch(DBException e) {
            user = null;
            throw new DBException(e);
        } finally {
            return user;
        }
    }
}
