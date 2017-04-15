package lv.javaguru.java2.servlet.dto;

public class TaskDTO {

    private String taskId;

    private String name;

    private String text;

    private String creationDateTime;

    private String deadlineDate;

    private String deadlineTime;

    private String deadline;

    private String isMainTask;

    private String priority;

    private String done;

    public TaskDTO(String taskId,
                   String name,
                   String text,
                   String creationDateTime,
                   String deadlineDate,
                   String deadlineTime,
                   String deadline,
                   String isMainTask,
                   String priority,
                   String done) {
        this.taskId = taskId;
        this.name = name;
        this.text = text;
        this.creationDateTime = creationDateTime;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.deadline = deadline;
        this.isMainTask = isMainTask;
        this.priority = priority;
        this.done = done;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIsMainTask() {
        return isMainTask;
    }

    public void setIsMainTask(String isMainTask) {
        this.isMainTask = isMainTask;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
