package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/14/2016.
 */
public class LoginServiceImpl implements LoginService {

    UserDAO userDAO = new UserDAOImpl();
    User user;

    @Override
    public User authenticate (String login, String password) {
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
