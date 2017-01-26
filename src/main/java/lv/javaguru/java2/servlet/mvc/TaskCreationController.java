package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.tasks.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TaskCreationController{

    @Autowired
    TaskFactory taskFactory;

    @RequestMapping(value="create_task", method = {RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) return new ModelAndView("/", "model", null); //Remove when security implemented
        else return new ModelAndView("taskCreationPage", "model", null);
    }

    @RequestMapping(value="create_task", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {

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
            return new ModelAndView("taskCreationPage", "data",
                    "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
        }

        return new ModelAndView("taskCreationPage", "data",
                "<div class=\"alert alert-success\" role=\"alert\">" + "Task created" + "</div>");
    }

}
