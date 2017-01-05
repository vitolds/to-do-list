package lv.javaguru.java2.service.tasks;


import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TaskValidatorImpl implements TaskValidator {

    private final int MAX_NAME_LENGTH = 60;
    private final int MAX_TEXT_LENGTH = 255;
    private final int MIN_PRIORITY = 1;
    private final int MAX_PRIORITY = 3;
    public static final String CHECKBOX_VALUE = "1";

    @Override
    public void validateTask(String name,
                             String text,
                             String deadline,
                             String isMain,
                             String priority) {

        // Task name validation
        try {
            validateName(name);
        } catch(IllegalArgumentException e) {
            throw e;
        }

        //Task text validation
        try {
            validateText(text);
        } catch(IllegalArgumentException e) {
            throw e;
        }

        //Task deadline validation
        try {
            validateDeadline(deadline);
        } catch(IllegalArgumentException e) {
            throw e;
        }

        //Task isMain validation
        try {
            validateIsMain(isMain);
        } catch(IllegalArgumentException e) {
            throw e;
        }

        //Task priority validation
        try {
            validatePriority(priority);
        } catch(IllegalArgumentException e) {
            throw e;
        }

        //Task isDone validation
//        try {
//            validateIsDone(isDone);
//        } catch(IllegalArgumentException e) {
//            throw e;
//        }

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

//        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
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
        if (isDone.equals("") || isDone.equals(CHECKBOX_VALUE) ) {
            return;
        } else {
            throw new IllegalArgumentException("Task done attribute is not valid");
        }
    }

    @Override
    public void validateIsMain(String isMain) {
        if (isMain == null) {
            return;
        } else if (isMain.equals(CHECKBOX_VALUE)) {
            return;
        } else {
            throw new IllegalArgumentException("Task main attribute is not valid");
        }
    }
}
