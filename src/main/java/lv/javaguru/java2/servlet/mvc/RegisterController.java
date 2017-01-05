package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserBuilder;
import lv.javaguru.java2.service.RegisterService;
import lv.javaguru.java2.service.RegisterServiceImpl;
import lv.javaguru.java2.service.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Vitolds on 12/17/2016.
 */
@Component
public class RegisterController implements MVCController {

    @Autowired
    RegisterService registerService;

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/redirect.jsp", "/java2");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        User user = createUser()
                .withUserName(req.getParameter("userName"))
                .withEmail(req.getParameter("email"))
                .withFirstName(req.getParameter("firstName"))
                .withLastName(req.getParameter("lastName"))
                .build();
        user.setPassword(req.getParameter("password"));

        ValidatorMessage validatorMessage = registerService.registerUser(user);

        if (validatorMessage.isSuccess()) return new MVCModel("/homePage.jsp",
                "<span style=\"color: green\">" + validatorMessage.getMessage() + "</span>");
            else return new MVCModel("/homePage.jsp",
                "<span style=\"color: red\">" + validatorMessage.getMessage() + "</span>");

    }
}
