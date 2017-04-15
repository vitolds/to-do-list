package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.SecurityService;
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
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    TaskFactory taskFactory;

    @RequestMapping(value="create_task", method = {RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {
        return new ModelAndView("taskCreationPage", "data", "");
    }

    @RequestMapping(value="create_task", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {

        String deadlineDate = req.getParameter("deadlineDate");
        String deadlineTime = req.getParameter("deadlineTime");
        String deadline;
        if (deadlineDate.isEmpty() && deadlineTime.isEmpty()) {
            deadline = "";
        } else if(deadlineTime.isEmpty()) {
            deadline = deadlineDate + " 23:59";
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

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

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
