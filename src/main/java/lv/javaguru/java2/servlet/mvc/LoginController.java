package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/18/2016.
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value="login", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {
        User user;
        HttpSession session = req.getSession();
        try {
            user = loginService.authenticate(req.getParameter("userName"), req.getParameter("password"));
        } catch (DBException e) {
            return new ModelAndView("homePage", "data", "<span style=\\\"color:red\\\">There was an error when communicating to the server</span>");
        }

        if (user == null) return new ModelAndView("homePage", "data", "<span style=\\\"color:red\\\">Wrong username and/or password</span>");
        else {
            session.setAttribute("user", user);
            return new ModelAndView("redirect", "data", "/java2/profile");
        }
    }
}
