package lv.javaguru.java2.servlet.dto;


/**
 * Created by vitol on 29/01/2017.
 */
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private float coins;
    private boolean isVisible;
    private TaskDTO task;
    private int taskCount;
    private int taskSlots;

    public UserDTO() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCoins() {
        return coins;
    }

    public void setCoins(float coins) {
        this.coins = coins;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getTaskSlots() {
        return taskSlots;
    }

    public void setTaskSlots(int taskSlots) {
        this.taskSlots = taskSlots;
    }
}
