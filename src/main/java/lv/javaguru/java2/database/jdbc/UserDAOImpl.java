package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAOImpl implements UserDAO {

    public void create(User user, String passW) throws DBException {
        if (user == null || passW == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into USERS values (default, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, getHashedPassword(passW));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setFloat(6, user.getCoins());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                user.setUserId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    public User getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where UserID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUserName(resultSet.getString("UserName"));
                user.setEmail(resultSet.getString("Email"));
                user.setCoins(resultSet.getFloat("Coins"));
            }
            return user;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> getAll() throws DBException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUserName(resultSet.getString("UserName"));
                user.setEmail(resultSet.getString("Email"));
                user.setCoins(resultSet.getFloat("Coins"));
                users.add(user);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from USERS where UserID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(User user) throws DBException {
        if (user == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update USERS set FirstName = ?, LastName = ?, UserName = ?, Email = ?, Coins = ?" +
                            "where UserID = ?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setFloat(5, user.getCoins());
            preparedStatement.setLong(6, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public User getByLogin(String userName, String passW) {
        Connection connection = null;
        User user = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where UserName = ? and passW = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, getHashedPassword(passW));
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUserName(resultSet.getString("UserName"));
                user.setEmail(resultSet.getString("Email"));
                user.setCoins(resultSet.getFloat("Coins"));
            }
        } catch (Throwable e) {
            System.out.println("Exception while executing UserDAOImpl.getByLogin()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

        return user;
    }

    @Override
    public boolean userNameExists(String userName) {

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where UserName = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
            if (!preparedStatement.executeQuery().next()) return false;
                    else return true;
        } catch (Throwable e) {
            System.out.println("Exception while executing UserDAOImpl.userNameExists()");
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean emailExists(String email) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where Email = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            if (!preparedStatement.executeQuery().next()) return false;
                    else return true;
        } catch (Throwable e) {
            System.out.println("Exception while executing UserDAOImpl.userNameExists()");
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    // Method used to hash a password with the specified algorithm
    private String getHashedPassword(String passW) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-1");
        passwordEncryptor.setPlainDigest(true);
        return passwordEncryptor.encryptPassword(passW);

        // Improve the implementation by adding the salt to the hash
    }
}
