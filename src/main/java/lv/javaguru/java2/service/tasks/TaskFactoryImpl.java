package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.database.TaskDAO;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Transactional
public class TaskFactoryImpl implements TaskFactory {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TaskValidator taskValidator;

    private final String DATETIME_STRING_FORMAT = "DD.MM.yyyy HH:mm";

    @Override
    public Task create(TaskDTO taskDTO, User user) {

        try {
            taskValidator.validateTask(taskDTO);
        }
        catch(IllegalArgumentException e) {
            throw e;
        }

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        task.setDeadline(Utils.convertStringToTimestamp(taskDTO.getDeadline(), DATETIME_STRING_FORMAT));
        task.setUserID(user.getUserId());
        if (taskDTO.getIsMainTask() == null) {
            task.setMainTask(false);
        } else if (taskDTO.getIsMainTask().equals(TaskValidatorImpl.CHECKBOX_VALUE)) {
            task.setMainTask(true);
        }
        task.setPriority(Integer.parseInt(taskDTO.getPriority()));
        task.setDone(false);

        taskDAO.create(task);
        
        return task;
    }
}
