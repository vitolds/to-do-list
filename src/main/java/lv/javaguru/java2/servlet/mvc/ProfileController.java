package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/23/2016.
 */
@Controller
public class ProfileController {

    @RequestMapping(value="profile", method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) return new ModelAndView("/", "model", null); //Remove when security implemented
        User user = (User) session.getAttribute("user");

        return new ModelAndView("profilePage", "data", user);
    }
}
