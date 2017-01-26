package lv.javaguru.java2.service.tasks;


import lv.javaguru.java2.servlet.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TaskValidatorImpl implements TaskValidator {

    private final int MAX_NAME_LENGTH = 60;
    private final int MAX_TEXT_LENGTH = 255;
    private final int MIN_PRIORITY = 1;
    private final int MAX_PRIORITY = 3;
    static final String CHECKBOX_VALUE = "1";

    @Override
    public void validateTask(TaskDTO taskDTO) throws IllegalArgumentException {

        // Task name validation
        validateName(taskDTO.getName());

        //Task text validation
        validateText(taskDTO.getText());

        //Task deadline validation
        validateDeadline(taskDTO.getDeadline());

        //Task isMain validation
        validateIsMain(taskDTO.getIsMainTask());

        //Task priority validation
        validatePriority(taskDTO.getPriority());

        //Task done validation
        validateIsDone(taskDTO.getDone());
    }

    @Override
    public void validateName(String name) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please enter task name");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Task name must match teh length (" + MAX_NAME_LENGTH + ")");
        }

    }

    @Override
    public void validateText(String text) {

        if (text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Task text must match teh length (" + MAX_TEXT_LENGTH + ")");
        }

    }

    @Override
    public void validateDeadline(String deadline) {

        SimpleDateFormat format = new java.text.SimpleDateFormat("DD.MM.yyyy HH:mm");

        if (deadline.isEmpty()) {
            return;
        }

        try {
            format.parse(deadline);
        }
        catch(ParseException e)
        {
            throw new IllegalArgumentException("Task deadline is not valid");
        }

    }

    @Override
    public void validatePriority(String priority) {
        try {
            int intPriority = Integer.parseInt(priority);
            if (intPriority < MIN_PRIORITY || intPriority > MAX_PRIORITY) {
                throw new IllegalArgumentException("Task priority is not valid");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task priority is not valid");
        }
    }

    @Override
    public void validateIsDone(String isDone) {
        if (isDone.equals("") || isDone.equals(CHECKBOX_VALUE)) {
        } else {
            throw new IllegalArgumentException("Task done attribute is not valid");
        }
    }

    @Override
    public void validateIsMain(String isMain) {
        if (isMain == null) {
        } else if (isMain.equals(CHECKBOX_VALUE) || isMain.equals("")) {
        } else {
            throw new IllegalArgumentException("Task main attribute is not valid");
        }
    }
}
