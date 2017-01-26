package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.servlet.dto.TaskDTO;

public interface TaskValidator {

    void validateTask(TaskDTO taskDTO);

    void validateName(String name);
    void validateText(String text);
    void validateDeadline(String deadline);
    void validatePriority(String priority);
    void validateIsDone(String isDone);
    void validateIsMain(String isMain);

}
