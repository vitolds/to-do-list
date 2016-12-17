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
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        User user = createUser()
                .withUserName(req.getParameter("userName").toString())
                .withEmail(req.getParameter("email").toString())
                .withFirstName(req.getParameter("firstName").toString())
                .withLastName(req.getParameter("lastName").toString())
                .build();

        ValidatorMessage validatorMessage = registerService.registerUser(user, req.getParameter("password").toString());

        return new MVCModel("/homePage.jsp", validatorMessage.getMessage());
    }
}
