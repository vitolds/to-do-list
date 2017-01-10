package lv.javaguru.java2.service.tasks;


import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;

public interface TaskFactory {

    Task create(TaskDTO taskDTO, User user);
}
