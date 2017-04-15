package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.service.TaskDTOService;
import lv.javaguru.java2.servlet.dto.TaskDTO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.security.SecurityService;
import lv.javaguru.java2.service.tasks.TaskFactory;
import lv.javaguru.java2.service.tasks.TaskService;
import lv.javaguru.java2.servlet.dto.TasksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    TaskDTOService taskDTOService;

    @RequestMapping(value="tasks", method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        List<Task> tasks = taskService.getAllTasksByUser(user);

        List<TaskDTO> undoneTasksDTO = new ArrayList<>();
        List<TaskDTO> doneTasksDTO = new ArrayList<>();
        TaskDTO taskDTO;
        boolean noMainTask = true;
        TasksDTO tasksDTO = new TasksDTO();
        if (tasks != null) {
            for (Task task : tasks) {
                if (task.isDone()) {
                    doneTasksDTO.add(taskDTOService.getTaskDTO(task));
                } else {
                    taskDTO = taskDTOService.getTaskDTO(task);
                    undoneTasksDTO.add(taskDTO);
                    if (noMainTask && task.isMainTask()) {
                        tasksDTO.setMainTask(taskDTO);
                        noMainTask = false;
                    }

                }
            }
        }

        tasksDTO.setDoneTasks(doneTasksDTO);
        tasksDTO.setUndoneTasks(undoneTasksDTO);

        return new ModelAndView("tasksPage", "data", tasksDTO);
    }

    @RequestMapping(value="tasks", method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {
        String cmd = req.getParameter("cmd");
        if (cmd != null) {
            String taskId = req.getParameter("taskId");
            switch (cmd) {
                case "markDone":
                    System.out.println("mark done");
                    taskService.markDone(Integer.parseInt(taskId));
                    break;
                case "markUndone":
                    try {
                        taskService.markUndone(Integer.parseInt(taskId));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        return new ModelAndView("ajaxPage", "data",
                                "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
                    }
                    break;
                case "markMain":
                    taskService.markMain(Integer.parseInt(taskId));
                    break;
                case "markNotMain":
                    taskService.markNotMain(Integer.parseInt(taskId));
                    break;
            }
            return new ModelAndView("ajaxPage", "data", 1);
        }

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
                req.getParameter("taskId"),
                req.getParameter("taskName"),
                req.getParameter("taskText"),
                "",
                deadlineDate,
                deadlineTime,
                deadline,
                "",
                req.getParameter("taskPriority"),
                "");

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        try {
            taskService.update(taskDTO, user);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ModelAndView("ajaxPage", "data",
                    "<div class=\"alert alert-danger\" role=\"alert\">" + e.getMessage() + "</div>");
        }

        return new ModelAndView("ajaxPage", "data",
                "<div class=\"alert alert-success\" role=\"alert\">" + "Task updated" + "</div>");
    }
}
