package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.TaskDTO;
import lv.javaguru.java2.domain.User;
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

    @RequestMapping(value="tasks", method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) return new ModelAndView("/", "model", null); //Remove when security implemented//Remove when security implemented

        User user = (User) session.getAttribute("user");

        List<Task> tasks = taskService.getAllTasksByUser(user);

        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (Task task: tasks) {
            tasksDTO.add(convertTaskToTaskDTO(task));
        }

        return new ModelAndView("tasksPage", "data", tasksDTO);
    }

    @RequestMapping(value="tasks", method={RequestMethod.POST})
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

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

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
