package lv.javaguru.java2.database.springJPA;

import lv.javaguru.java2.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vitol on 21/01/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
