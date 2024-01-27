package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/task/{taskId}")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTaskList(@PathVariable("taskId") Long taskId,
                              HttpServletRequest request,
                              Model model) {


        TaskModuleResponse task = taskService.getTask(taskId);
        Account account = (Account) request.getSession().getAttribute("account");

        model.addAttribute("userId", account.getUserId());
        model.addAttribute("task", task);

        return "task/main";
    }
}
