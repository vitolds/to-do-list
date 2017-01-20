package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {

    @RequestMapping(value="/", method={RequestMethod.GET})
    public ModelAndView processGet (HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) { //Remove when security implemented
            return new ModelAndView("homePage", "model", null);
        } else {
            return new ModelAndView("redirect", "data", "/profile");
        }
    }
}
