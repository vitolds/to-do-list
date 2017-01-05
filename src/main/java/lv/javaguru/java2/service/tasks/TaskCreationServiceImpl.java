package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.database.TaskDAO;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Transactional
public class TaskCreationServiceImpl implements TaskCreationService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TaskValidator taskValidator;

    private final String DATETIME_STRING_FORMAT = "DD.MM.yyyy HH:mm";

    @Override
    public Task createTask(String name,
                           String text,
                           String deadline,
                           User user,
                           String isMainTask,
                           String priority) {

        try {
            taskValidator.validateTask(name, text, deadline, isMainTask, priority);
        }
        catch(IllegalArgumentException e) {
            throw e;
        }

        Task task = new Task();
        task.setName(name);
        task.setText(text);
        task.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        task.setDeadline(Utils.convertStringToTimestamp(deadline, DATETIME_STRING_FORMAT));
        task.setUserID(user.getUserId());
        if (isMainTask == null) {
            task.setMainTask(false);
        } else if (isMainTask.equals(TaskValidatorImpl.CHECKBOX_VALUE)) {
            task.setMainTask(true);
        }
        task.setPriority(Integer.parseInt(priority));
        task.setDone(false);

        taskDAO.create(task);
        
        return task;
    }
}
