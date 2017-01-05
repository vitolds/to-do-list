package lv.javaguru.java2.service.tasks;


import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;

public interface TaskCreationService {

    Task createTask(String name,
                    String text,
                    String deadline,
                    User user,
                    String isMainTask,
                    String priority);
}
