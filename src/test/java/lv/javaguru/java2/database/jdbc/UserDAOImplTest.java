//package lv.javaguru.java2.database.jdbc;
//
//import lv.javaguru.java2.database.DBException;
//import lv.javaguru.java2.database.UserDAO;
//import lv.javaguru.java2.domain.User;
//import lv.javaguru.java2.service.LoginService;
//import lv.javaguru.java2.service.LoginServiceImpl;
//import lv.javaguru.java2.service.RegisterService;
//import lv.javaguru.java2.service.RegisterServiceImpl;
//import org.junit.Test;
//
//import static lv.javaguru.java2.domain.UserBuilder.createUser;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class UserDAOImplTest extends DBUnitTestCase {
//
//    private UserDAO userDAO = new UserDAOImpl();
//    private LoginService loginService = new LoginServiceImpl();
//    private RegisterService registerService = new RegisterServiceImpl();
//
//    @Override
//    protected String getDatabaseFile() {
//        return "dbscripts/UserDAOImplTest.xml";
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        User user = createUser()
//                .withFirstName("F")
//                .withLastName("L")
//                .withUserName("something5")
//                .withEmail("salgbafnklfasf@gmail.com").build();
//
//        System.out.println(registerService.registerUser(user, "P") ? registerService.SUCCESS_MESSAGE : registerService.getMessage());
//
//        try {
//            System.out.println(loginService.authenticate("something", "P"));
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//
//
////        User userFromDB = userDAO.getById(user.getUserId());
////        assertNotNull(userFromDB);
////        assertEquals(user.getUserId(), userFromDB.getUserId());
////        assertEquals(user.getFirstName(), userFromDB.getFirstName());
////        assertEquals(user.getLastName(), userFromDB.getLastName());
//    }
//
//}
