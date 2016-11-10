package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface TaskDAO {

    void create(Task task);

    Task getById(Long id);

    void delete(Long id);

    void update(Task task);

    List<Task> getByUser(User user);

}
