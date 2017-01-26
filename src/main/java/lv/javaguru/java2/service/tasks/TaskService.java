package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasksByUser(User user);

    void update(TaskDTO task, User user);

    void markDone(int taskId);

    void markUndone(int taskId);

    void markMain(int taskId);

    void markNotMain(int taskId);

}
