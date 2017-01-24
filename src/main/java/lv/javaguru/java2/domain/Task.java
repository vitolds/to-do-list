package lv.javaguru.java2.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TaskId", nullable = false)
    private long taskId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Text")
    private String text;

    @Column(name = "CreationDateTime", nullable = false)
    private Timestamp creationDateTime;

    @Column(name = "Deadline")
    private Timestamp deadline;

//    @Column(name = "UserId", nullable = false)
//    private long userID;

    @Column(name = "MainTask")
    @Type(type = "org.hibernate.type.BooleanType")
    private boolean isMainTask;

    @Column(name = "Priority", columnDefinition = "TINYINT")
    private int priority;

    @Column(name = "Done")
    @Type(type = "org.hibernate.type.BooleanType")
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
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

//    public long getUserID() {
//        return userID;
//    }
//
//    public void setUserID(long userID) {
//        this.userID = userID;
//    }

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
