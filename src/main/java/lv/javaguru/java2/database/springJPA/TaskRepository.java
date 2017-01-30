package lv.javaguru.java2.database.springJPA;

import lv.javaguru.java2.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vitol on 22/01/2017.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByUserId(long userId);
    List<Task> findByUserIdOrderByPriorityDescDeadlineAsc(long userId);
    List<Task> findByUserIdAndIsMainTaskAndDone(long userId, boolean isMainTask, boolean done);
}
