package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.config.SpringAppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MVCFilter implements Filter {

    private Map<String, MVCController> controllers;

    private ApplicationContext springContext;

    private MVCController getBean (Class<?> clazz) {
        return (MVCController) springContext.getBean(clazz);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            springContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        controllers = new HashMap<>();
        controllers.put("/hello", getBean(HelloWorldController.class));
        controllers.put("/", getBean(HomePageController.class));
        controllers.put("/register", getBean(RegisterController.class));
        controllers.put("/login", getBean(LoginController.class));
        controllers.put("/profile", getBean(ProfileController.class));
        controllers.put("/create_task", getBean(TaskCreationController.class));
        controllers.put("/logout", getBean(LogoutController.class));
        controllers.put("/tasks", getBean(TasksController.class));
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession httpSession = req.getSession();

        String contextURI = req.getServletPath();
        String method = req.getMethod();

        if (contextURI.contains("/css")) {
            filterChain.doFilter(request, response);
        } else if (httpSession.getAttribute("user") == null && !contextURI.equals("/") && !contextURI.equals("/register")
                && !contextURI.equals("/login")) {
            resp.sendRedirect("/java2");
        } else {
            MVCController controller = controllers.get(contextURI);
            MVCModel model;

            if (method.equalsIgnoreCase("GET")){
                model = controller.processGet(req);
            }
            else {
                model = controller.processPost(req);
            }
            req.setAttribute("data", model.getData());

            ServletContext context = req.getServletContext();
            RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
