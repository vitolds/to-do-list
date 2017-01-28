//package lv.javaguru.java2.database.jdbc;
//
//import lv.javaguru.java2.database.TaskDAO;
//import lv.javaguru.java2.domain.Task;
//import lv.javaguru.java2.domain.User;
//import org.junit.Test;
//
//import java.sql.Timestamp;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class TaskDAOImplTest extends DBUnitTestCase {
//
//    private TaskDAO taskDAO = new TaskDAOImpl();
//
//    @Override
//    protected String getDatabaseFile() {
//        return "dbscripts/TaskDAOImplTest.xml";
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        User user = new User();
////        user.setUserId((long) 1002);
//
////        Task task = new Task();
////        task.setName("N");
////        task.setText("T");
////        task.setCreationDateTime(Timestamp.valueOf("2016-11-11 10:10:10.0"));
////        task.setDeadline(Timestamp.valueOf("2016-11-11 10:10:10.0"));
////        task.setUser(user);
////        task.setMainTask(false);
////        task.setPriority(0);
////        task.setDone(false);
////
////        taskDAO.create(task);
////
////        Task taskFromDB = taskDAO.getById(task.getTaskId());
////        assertNotNull(taskFromDB);
////        assertEquals(task.getTaskId(), taskFromDB.getTaskId());
////        assertEquals(task.getName(), taskFromDB.getName());
////        assertEquals(task.getText(), taskFromDB.getText());
////        assertEquals(task.getCreationDateTime(), taskFromDB.getCreationDateTime());
////        assertEquals(task.getDeadline(), taskFromDB.getDeadline());
////        assertEquals(task.getUser().getUserId(), taskFromDB.getUser().getUserId());
////        assertEquals(task.getMainTask(), taskFromDB.getMainTask());
////        assertEquals(task.getPriority(), taskFromDB.getPriority());
////        assertEquals(task.getDone(), taskFromDB.getDone());
//    }
//
//}
