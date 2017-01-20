package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/24/2016.
 */
@Controller
public class LogoutController{

    @RequestMapping(value="logout", method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user")!=null) {
            session.setAttribute("user", null);
            return new ModelAndView("redirect", "data", "/java2");
        } else return new ModelAndView("redirect", "data", "/java2");
    }
}
