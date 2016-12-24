package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.List;

public interface UserDAO extends GenericHibernateDAO<User> {

    User getByLogin(String userName, String passW);

    boolean emailExists(String email);

    boolean userNameExists(String userName);
}
