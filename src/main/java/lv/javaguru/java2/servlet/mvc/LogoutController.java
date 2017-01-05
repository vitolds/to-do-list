package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/24/2016.
 */
@Component
public class LogoutController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user")!=null) {
            session.setAttribute("user", null);
            return new MVCModel("/redirect.jsp", "/java2");
        } else return new MVCModel("redirect.jsp", "/java2");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
