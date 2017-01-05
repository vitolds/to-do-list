package lv.javaguru.java2.service.tasks;

import lv.javaguru.java2.domain.Task;

public interface TaskValidator {

    void validateTask(String name,
                      String text,
                      String deadline,
                      String isMain,
                      String priority);

    void validateName(String name);
    void validateText(String text);
    void validateDeadline(String deadline);
    void validatePriority(String priority);
    void validateIsDone(String isDone);
    void validateIsMain(String isMain);

}
