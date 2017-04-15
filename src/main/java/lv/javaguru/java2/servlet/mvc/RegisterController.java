package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.RegisterService;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vitolds on 12/17/2016.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value="register", method={RequestMethod.GET})
    public ModelAndView processGet() {
        return new ModelAndView("register", "data", null);
    }

    @RequestMapping(value="register", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {

        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));

        ValidatorMessage validatorMessage = registerService.registerUser(user);

        return new ModelAndView("redirect", "data", "/java2/register" + validatorMessage.getMessage());
    }
}