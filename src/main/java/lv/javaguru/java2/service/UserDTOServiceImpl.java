package lv.javaguru.java2.service;

import lv.javaguru.java2.database.springJPA.TaskRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import lv.javaguru.java2.servlet.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vitol on 29/01/2017.
 */

@Component
public class UserDTOServiceImpl implements UserDTOService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCoins(user.getCoins());
        userDTO.setEmail(user.getEmail());
        userDTO.setVisible(user.isVisible());

        List<Task> tasks = taskRepository.findByUserIdAndIsMainTaskAndDone(user.getUserId(), true, false);
        Task task = null;
        int priority = 3;
        for (Task t : tasks) {
            if (t.getPriority() == priority) {
                task = t;
            } else if (t.getPriority() < priority) {
                task = t;
                priority--;
            }
        }
        if (task != null) {

            userDTO.setTask(task);
        }
        return userDTO;
    }

    @Override
    public User getUser(UserDTO userDTO) {
        return null;
    }
}
