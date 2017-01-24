package lv.javaguru.java2.domain;

import javax.persistence.*;
import javax.persistence.Id;

/**
 * Created by vitol on 21/01/2017.
 */
@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UserRoleId", nullable = false)
    private long userRoleId;

    @Column(name="Role", nullable = false)
    private String role;

    @Column(name="UserId", nullable = false)
    private long userId;

    @Transient
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
