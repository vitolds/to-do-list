package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface TaskDAO extends GenericHibernateDAO<Task>{

    List<Task> getAllByUser(User user);

}
