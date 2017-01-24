//package lv.javaguru.java2.database.jdbc;
//
//import lv.javaguru.java2.database.GenericHibernateDAO;
//import lv.javaguru.java2.database.GenericHibernateDAOImpl;
//import lv.javaguru.java2.database.UserDAO;
//import lv.javaguru.java2.service.LoginService;
//import lv.javaguru.java2.service.LoginServiceImpl;
//import lv.javaguru.java2.service.security.RegisterService;
//import lv.javaguru.java2.service.security.RegisterServiceImpl;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class UserDAOImplTest extends DBUnitTestCase {
//
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
////        User user = createUser()
////                .withFirstName("F")
////                .withLastName("L")
////                .withUserName("something5")
////                .withEmail("salgbafnklfasf@gmail.com")
////                .build();
////        user.setPassword("PP");
////
////        userDAO.create(user);
//
//        System.out.println(userDAO.getById(1));
////        User userFromDB = userDAO.getById(user.getUserId());
////        assertNotNull(userFromDB);
////        assertEquals(user.getUserId(), userFromDB.getUserId());
////        assertEquals(user.getFirstName(), userFromDB.getFirstName());
////        assertEquals(user.getLastName(), userFromDB.getLastName());
//    }
//
//}
