package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.servlet.dto.TaskDTO;

public interface TaskDTOService {

    TaskDTO getTaskDTO(Task task);
    Task getTask(TaskDTO taskDTO);

}
