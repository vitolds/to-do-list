package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDAOImpl extends GenericHibernateDAOImpl<Task> implements TaskDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Task> getAllByUser(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(
                "SELECT * FROM TASKS WHERE UserID = :userId")
                .addEntity(Task.class)
                .setParameter("userId", user.getUserId());
        List result = query.list();
        session.close();
        if (result.isEmpty()) return null;
        else return (List<Task>) result;
    }

}
