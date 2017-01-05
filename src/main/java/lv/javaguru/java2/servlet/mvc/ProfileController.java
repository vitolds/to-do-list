package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/23/2016.
 */
@Component
public class ProfileController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        return new MVCModel("/profilePage.jsp", user);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
