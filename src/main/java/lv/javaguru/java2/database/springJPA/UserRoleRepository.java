package lv.javaguru.java2.database.springJPA;

import lv.javaguru.java2.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vitol on 21/01/2017.
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
