package lv.javaguru.java2.service;


import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class TaskDTOServiceImpl implements TaskDTOService {
    @Override
    public TaskDTO getTaskDTO(Task task) {
        String strCreationDate = new SimpleDateFormat("dd.MM.yyyy").format(task.getCreationDateTime());
        String deadline;
        String deadlineDate;
        String deadlineTime;
//        try {
//            deadline = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(task.getDeadline());
//            deadlineDate = new SimpleDateFormat("dd.MM.yyyy").format(task.getDeadline());
//            deadlineTime = new SimpleDateFormat("HH:mm").format(task.getDeadline());
//            if (deadlineTime.equals("00:00")) {
//                deadlineTime = "";
//            }
//        } catch(NullPointerException e) {
//            deadlineDate = null;
//            deadlineTime = null;
//            deadline = null;
//        }
        Timestamp deadlineTimestamp = task.getDeadline();
        if (deadlineTimestamp.equals(Utils.NULL_TIMESTAMP)) {
            System.out.println("NULL TIMESTAMP");
            deadline = null;
            deadlineDate = null;
            deadlineTime = null;
        } else {
            deadline = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(task.getDeadline());
            deadlineDate = new SimpleDateFormat("dd.MM.yyyy").format(task.getDeadline());
            deadlineTime = new SimpleDateFormat("HH:mm").format(task.getDeadline());
            if (deadlineTime.equals("00:00")) {
                deadlineTime = "";
            }
        }

        TaskDTO taskDTO = new TaskDTO(
                String.valueOf(task.getTaskId()),
                task.getName(),
                task.getText(),
                strCreationDate,
                deadlineDate,
                deadlineTime,
                deadline,
                task.isMainTask() ? "1" : "0",
                String.valueOf(task.getPriority()),
                task.isDone() ? "1" : "0");
        return taskDTO;
    }

    @Override
    public Task getTask(TaskDTO taskDTO) {
        return null;
    }
}
