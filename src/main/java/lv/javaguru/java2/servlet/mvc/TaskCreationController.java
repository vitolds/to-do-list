package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.TaskDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.tasks.TaskCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class TaskCreationController implements MVCController{

    @Autowired
    TaskCreationService taskCreationService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    TaskDAO taskDAO;

    @Override
    public MVCModel processGet(HttpServletRequest req) {

        return new MVCModel("/taskCreationPage.jsp", "");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String name = req.getParameter("taskName");
        String text = req.getParameter("taskText");
        String deadlineDate = req.getParameter("deadlineDate");
        String deadlineTime = req.getParameter("deadlineTime");
        String deadline;
        if (!deadlineDate.isEmpty()) {
            if (!deadlineTime.isEmpty()) {
                deadline = deadlineDate + " " + deadlineTime;
            } else {
                deadline = deadlineDate + " 00:00";
            }
        } else {
            deadline = "";
        }
        String priority = req.getParameter("taskPriority");
        String isMainTask = req.getParameter("isMainTask");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        try {
            Task task = taskCreationService.createTask(name, text, deadline, user, isMainTask, priority);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new MVCModel("/taskCreationPage.jsp",
                    "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
        }

        return new MVCModel("/taskCreationPage.jsp",
                "<div class=\"alert alert-success\" role=\"alert\">" + "Task created" + "</div>");
    }

}
