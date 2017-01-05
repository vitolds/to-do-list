package lv.javaguru.java2.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TaskID", nullable = false)
    private int taskId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Text")
    private String text;

    @Column(name = "CreationDateTime", nullable = false)
    private Timestamp creationDateTime;

    @Column(name = "Deadline")
    private Timestamp deadline;

    @Column(name = "UserID", nullable = false)
    private int userID;

    @Column(name = "MainTask")
    @Type(type = "org.hibernate.type.BooleanType")
    private boolean isMainTask;

    @Column(name = "Priority", columnDefinition = "TINYINT")
    private int priority;

    @Column(name = "Done")
    @Type(type = "org.hibernate.type.BooleanType")
    private boolean done;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
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

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isMainTask() {
        return isMainTask;
    }

    public void setMainTask(boolean mainTask) {
        isMainTask = mainTask;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
