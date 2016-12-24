package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TaskDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAOImpl extends DAOImpl implements TaskDAO {

    public void create(Task task) throws DBException {
        if (task == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into TASKS values (default, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getText());
            preparedStatement.setTimestamp(3, task.getCreationDateTime());
            preparedStatement.setTimestamp(4, task.getDeadline());
            preparedStatement.setLong(5, task.getUser().getUserId());
            preparedStatement.setBoolean(6, task.getMainTask());
            preparedStatement.setInt(7, task.getPriority());
            preparedStatement.setBoolean(8, task.getDone());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                task.setTaskId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public Task getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from TASKS where TaskID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Task task = null;
            UserDAO userDAO = new UserDAOImpl();
            if (resultSet.next()) {
                task = new Task();
                task.setTaskId(resultSet.getLong("TaskID"));
                task.setName(resultSet.getString("Name"));
                task.setText(resultSet.getString("Text"));
                task.setCreationDateTime(resultSet.getTimestamp("CreationDateTime"));
                task.setDeadline(resultSet.getTimestamp("Deadline"));
                task.setUser((User) userDAO.getById(resultSet.getLong("UserID")));
                task.setMainTask(resultSet.getBoolean("MainTask"));
                task.setPriority(resultSet.getInt("Priority"));
                task.setDone(resultSet.getBoolean("Done"));
            }
            return task;
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Task> getByUser(User user) throws DBException {
        List<Task> tasks = new ArrayList<Task>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from TASKS where UserID = ?");
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDAO userDAO = new UserDAOImpl();
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getLong("TaskID"));
                task.setName(resultSet.getString("Name"));
                task.setText(resultSet.getString("Text"));
                task.setCreationDateTime(resultSet.getTimestamp("CreationDateTime"));
                task.setDeadline(resultSet.getTimestamp("Deadline"));
                task.setUser((User) userDAO.getById(resultSet.getLong("UserID")));
                task.setMainTask(resultSet.getBoolean("MainTask"));
                task.setPriority(resultSet.getInt("Priority"));
                task.setDone(resultSet.getBoolean("Done"));
                tasks.add(task);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TaskDAOImpl.getByUser()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return tasks;
    }

    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from TASKS where TaskID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(Task task) throws DBException {
        if (task == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update TASKS set Name = ?, Text = ?, CreationDateTime = ?, Deadline = ?, UserID = ?, " +
                            "MainTask = ?, Piority = ?, Done = ? where TaskID = ?");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getText());
            preparedStatement.setTimestamp(3, task.getCreationDateTime());
            preparedStatement.setTimestamp(4, task.getDeadline());
            preparedStatement.setLong(5, task.getUser().getUserId());
            preparedStatement.setBoolean(6, task.getMainTask());
            preparedStatement.setInt(7, task.getPriority());
            preparedStatement.setBoolean(8, task.getDone());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

}
