package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.tasks.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class TaskCreationController implements MVCController{

    @Autowired
    TaskFactory taskFactory;

    @Override
    public MVCModel processGet(HttpServletRequest req) {

        return new MVCModel("/taskCreationPage.jsp", "");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        String deadlineDate = req.getParameter("deadlineDate");
        String deadlineTime = req.getParameter("deadlineTime");
        String deadline;
        if (deadlineDate.isEmpty() && deadlineTime.isEmpty()) {
            deadline = "";
        } else if(deadlineTime.isEmpty()) {
            deadline = deadlineDate + " 00:00";
        } else {
            deadline = deadlineDate + " " + deadlineTime;
        }

        TaskDTO taskDTO = new TaskDTO(
                "",
                req.getParameter("taskName"),
                req.getParameter("taskText"),
                "",
                deadlineDate,
                deadlineTime,
                deadline,
                req.getParameter("isMainTask"),
                req.getParameter("taskPriority"),
                "");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        try {
            Task task = taskFactory.create(taskDTO, user);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new MVCModel("/taskCreationPage.jsp",
                    "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
        }

        return new MVCModel("/taskCreationPage.jsp",
                "<div class=\"alert alert-success\" role=\"alert\">" + "Task created" + "</div>");
    }

}
