package lv.javaguru.java2.servlet.dto;

import java.util.List;

public class TasksDTO {

    private List<TaskDTO> undoneTasks;

    private List<TaskDTO> doneTasks;

    private TaskDTO mainTask;

    public List<TaskDTO> getUndoneTasks() {
        return undoneTasks;
    }

    public void setUndoneTasks(List<TaskDTO> undoneTasks) {
        this.undoneTasks = undoneTasks;
    }

    public List<TaskDTO> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(List<TaskDTO> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public TaskDTO getMainTask() {
        return mainTask;
    }

    public void setMainTask(TaskDTO mainTask) {
        this.mainTask = mainTask;
    }
}
