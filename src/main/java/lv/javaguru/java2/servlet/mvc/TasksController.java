package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.SecurityService;
import lv.javaguru.java2.service.tasks.TaskFactory;
import lv.javaguru.java2.service.tasks.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TasksController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value="tasks", method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        List<Task> tasks = taskService.getAllTasksByUser(user);

        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (Task task: tasks) {
            tasksDTO.add(convertTaskToTaskDTO(task));
        }

        return new ModelAndView("tasksPage", "data", tasksDTO);
    }

    @RequestMapping(value="tasks", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {
        String cmd = req.getParameter("cmd");
        if (cmd != null) {
            if (cmd.equals("markDone")) {
                String taskId = req.getParameter("taskId");
                taskService.markDone(Integer.parseInt(taskId));
                return new ModelAndView("ajaxPage", "data", "");
            } else if (cmd.equals("markUndone")) {
                String taskId = req.getParameter("taskId");
                taskService.markUndone(Integer.parseInt(taskId));
                return new ModelAndView("ajaxPage", "data", "");
            }
        }

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
                req.getParameter("taskId"),
                req.getParameter("taskName"),
                req.getParameter("taskText"),
                "",
                deadlineDate,
                deadlineTime,
                deadline,
                req.getParameter("isMainTask"),
                req.getParameter("taskPriority"),
                req.getParameter("isDoneTask"));

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        try {
            taskService.update(taskDTO, user);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ModelAndView("ajaxPage", "data",
                    "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
        }

        return new ModelAndView("ajaxPage", "data",
                "<div class=\"alert alert-success\" role=\"alert\">" + "Task created" + "</div>");

    }

    private TaskDTO convertTaskToTaskDTO(Task task) {
        String strCreationDate = new SimpleDateFormat("dd.MM.yyyy").format(task.getCreationDateTime());
        String deadline;
        String deadlineDate;
        String deadlineTime;
        try {
            deadline = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(task.getDeadline());
            deadlineDate = new SimpleDateFormat("dd.MM.yyyy").format(task.getDeadline());
            deadlineTime = new SimpleDateFormat("HH:mm").format(task.getDeadline());
            if (deadlineTime.equals("00:00")) {
                deadlineTime = "";
            }
        } catch(NullPointerException e) {
            deadlineDate = null;
            deadlineTime = null;
            deadline = null;
        }
        TaskDTO taskDTO = new TaskDTO(
                String.valueOf(task.getTaskId()),
                task.getName(),
                task.getText(),
                strCreationDate,
                deadlineDate,
                deadlineTime,
                deadline,
                task.isMainTask() ? "1" : "0",
                String.valueOf(task.getPriority()),
                task.isDone() ? "1" : "0");
        return taskDTO;
    }
}
