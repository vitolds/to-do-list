package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.database.springJPA.TaskRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskValidator taskValidator;

    private final String DATETIME_STRING_FORMAT = "DD.MM.yyyy HH:mm";

    @Override
    public List<Task> getAllTasksByUser(User user) {

        List<Task> tasks = taskRepository.findByUserId(user.getUserId());
        return tasks;
    }

    @Override
    public void update(TaskDTO taskDTO, User user) {
        try {
            taskValidator.validateTask(taskDTO);
        }
        catch(IllegalArgumentException e) {
            throw e;
        }

        Task task = taskRepository.findOne(Long.parseLong(taskDTO.getTaskId()));
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        task.setDeadline(Utils.convertStringToTimestamp(taskDTO.getDeadline(), DATETIME_STRING_FORMAT));
        task.setUser(user);
        if (taskDTO.getIsMainTask() == null) {
            task.setMainTask(false);
        } else if (taskDTO.getIsMainTask().equals(TaskValidatorImpl.CHECKBOX_VALUE)) {
            task.setMainTask(true);
        }
        task.setPriority(Integer.parseInt(taskDTO.getPriority()));
        if (taskDTO.getDone() == null) {
            task.setDone(false);
        } else if (taskDTO.getDone().equals("")){
            task.setDone(false);
        } else if (taskDTO.getDone().equals(TaskValidatorImpl.CHECKBOX_VALUE)) {
            task.setDone(true);
        }

        taskRepository.save(task);
    }

    @Override
    public void markDone(int taskId) {
        Task task = taskRepository.findOne((long) taskId);
        task.setDone(true);
    }

    @Override
    public void markUndone(int taskId) {
        Task task = taskRepository.findOne((long) taskId);
        task.setDone(false);
    }
}
