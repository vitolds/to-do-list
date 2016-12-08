package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest req) {

        return new MVCModel("/helloWorld.jsp", "Hello WWW world from Java!");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }

}
