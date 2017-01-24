package lv.javaguru.java2.database.springJPA;

import lv.javaguru.java2.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vitol on 22/01/2017.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
