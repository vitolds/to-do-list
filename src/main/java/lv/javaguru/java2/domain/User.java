package lv.javaguru.java2.domain;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UserId")
    private long userId;
    @Column(name="FirstName")
    private String firstName;
    @Column(name="LastName")
    private String lastName;
    @Column(name="UserName", nullable = false)
    private String username;
    @Column(name="Email", nullable = false)
    private String email;
    @Column(name="Coins")
    private float coins;
    @Column(name="PassW", nullable = false)
    private String password;

    @Transient
    private List<UserRole> roles;
    @Transient
    private List<Task> tasks;

    public User() {

    }

    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getUsername() { return username; }

    public void setUsername(String userName) { this.username = userName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public float getCoins() { return coins; }

    public void setCoins(float coins) { this.coins = coins; }

    public void setPassword(String password) { this.password = password;}

    public String getPassword() {return this.password;}
}
