package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HomePageController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {

        return new MVCModel("/homePage.jsp", "Fields with <span style=\"color: red\">\" * \"</span> are necessary");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }

}
