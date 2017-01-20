package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserBuilder;
import lv.javaguru.java2.service.RegisterService;
import lv.javaguru.java2.service.RegisterServiceImpl;
import lv.javaguru.java2.service.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Vitolds on 12/17/2016.
 */
@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping(value="register", method={RequestMethod.GET})
    public ModelAndView processGet() {
        return new ModelAndView("redirect", "data", "/");
    }

    @RequestMapping(value="register", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {
        User user = createUser()
                .withUserName(req.getParameter("userName"))
                .withEmail(req.getParameter("email"))
                .withFirstName(req.getParameter("firstName"))
                .withLastName(req.getParameter("lastName"))
                .build();
        user.setPassword(req.getParameter("password"));

        ValidatorMessage validatorMessage = registerService.registerUser(user);

        if (validatorMessage.isSuccess()) return new ModelAndView("homePage", "data",
                "<span style=\"color: green\">" + validatorMessage.getMessage() + "</span>");
        else return new ModelAndView("homePage", "data",
                "<span style=\"color: red\">" + validatorMessage.getMessage() + "</span>");

    }
}