package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.CoinService;
import lv.javaguru.java2.service.CoinServiceImpl;
import lv.javaguru.java2.service.Utils;
import lv.javaguru.java2.service.tasks.TaskFactory;
import lv.javaguru.java2.service.tasks.TaskFactoryImpl;
import lv.javaguru.java2.service.tasks.TaskValidatorImpl;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import org.junit.Test;

import java.sql.Timestamp;

public class CoinServiceImplTest {

    @Test
    public void test() {
//        User user = new User();
//        user.setUserId(1);
//
//        Task task = new Task();
//        task.setName("N");
//        task.setText("T");
//        task.setCreationDateTime(Timestamp.valueOf("2017-01-20 00:00:00.0"));
//        task.setDeadline(Timestamp.valueOf("2017-01-24 00:00:00.0"));
////        task.setUser(user);
//        task.setMainTask(false);
//        task.setPriority(1);
//        task.setDone(false);

        TaskDTO taskDTO = new TaskDTO(
                "",
                "Name",
                "Text",
                "",
                "",
                "",
                "",
                "0",
                "1",
                "");

//        TaskFactory taskFactory = new TaskFactoryImpl();

        String dtFormat = "DD.MM.yyyy HH:mm";

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        task.setDeadline(Utils.convertStringToTimestamp(taskDTO.getDeadline(), dtFormat));
//        task.setUserID(user.getUserId());
        if (taskDTO.getIsMainTask() == null) {
            task.setMainTask(false);
        } else if (taskDTO.getIsMainTask().equals("1")) {
            task.setMainTask(true);
        }
        task.setPriority(Integer.parseInt(taskDTO.getPriority()));
        task.setDone(false);


        CoinService coinService = new CoinServiceImpl();
        coinService.addCoinsToUser(task);


    }
}
