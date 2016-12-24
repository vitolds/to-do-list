package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/18/2016.
 */
@Component
public class LoginController implements MVCController {

    @Autowired
    LoginService loginService;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/redirect.jsp", "/java2");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        User user;
        HttpSession session = req.getSession();
        try {
            user = loginService.authenticate(req.getParameter("userName"), req.getParameter("password"));
        } catch (DBException e) {
            return new MVCModel("/homePage.jsp", "<span style=\"color:red\">There was an error when communicating to the server</span>");
        }

        if (user == null) return new MVCModel("/homePage.jsp", "<span style=\"color:red\">Wrong username and/or password</span>");
                else {
                    session.setAttribute("user", user);
                    return new MVCModel("/redirect.jsp", "/java2/profile");
                }
    }
}
