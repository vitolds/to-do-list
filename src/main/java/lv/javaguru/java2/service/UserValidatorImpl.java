package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

/**
 * Created by Vitolds on 11/15/2016.
 */
public class UserValidatorImpl implements UserValidator {

    UserDAO userDAO = new UserDAOImpl();

    private byte usernameLength = 16;
    private int emailLength = 255;

    private String message = null;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean validateUsername(String username) {

        if (username.length() > usernameLength)  {
            message = "Username must match the length (" + usernameLength + ")";
            return false;
        }
        if (userDAO.userNameExists(username)) {
            message = "Account with that username already exists";
            return false;
        }
        message = null;
        return true;
    }

    @Override
    public boolean validateEmail(String email) {

        if (email.length() > emailLength) {
            message = "Email must match the length (" + emailLength + ")";
            return false;
        }
        if (userDAO.emailExists(email)) {
            message = "Account with that email already exists";
            return false;
        }
        if (!valEmailSynt(email)) {
            message = "Invalid email";
            return false;
        }
        message = null;
        return true;
    }

    private boolean valEmailSynt(String email) {
        String[] substrs = email.split("@");
        if (substrs.length != 2) return false;
        else return true;

        // Upgrade
    }
}
