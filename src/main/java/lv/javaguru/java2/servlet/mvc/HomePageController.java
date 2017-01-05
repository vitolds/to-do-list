package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class HomePageController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            return new MVCModel("/homePage.jsp", "");
        } else {
            return new MVCModel("/redirect.jsp", "/java2/profile");
        }
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }

}