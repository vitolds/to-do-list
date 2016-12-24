package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl extends GenericHibernateDAOImpl<User> implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getByLogin(String userName, String passW) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(
                "SELECT * FROM USERS WHERE UserName = :userName AND PassW = :passW")
                .addEntity(User.class)
                .setParameter("userName", userName)
                .setParameter("passW", passW);
        List result = query.list();
        session.close();
        if (result.isEmpty()) return null;
        else return (User) result.get(0);
    }

    @Override
    public boolean userNameExists(String userName) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(
                "SELECT * FROM USERS WHERE UserName = :userName")
                .setParameter("userName", userName);
        List result = query.list();
        session.close();
        if (result.isEmpty()) return false;
        else return true;
    }

    @Override
    public boolean emailExists(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(
                "SELECT * FROM USERS WHERE Email = :email")
                .setParameter("email", email);
        List result = query.list();
        session.close();
        if (result.isEmpty()) return false;
        else return true;
    }
}
