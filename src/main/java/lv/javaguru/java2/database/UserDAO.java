package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.List;

public interface UserDAO {

    void create(User user, String passW);

    User getById(Long id);

    void delete(Long id);

    void update(User user);

    List<User> getAll();

    User getByLogin(String userName, String passW);
}
