package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task/{taskId}/update")
public class TaskUpdateController {

    private final TaskService taskService;

    public TaskUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTaskUpdateForm(@PathVariable("taskId") Long taskId,
                                    Model model) {

        TaskModuleResponse task = taskService.getTask(taskId);
        model.addAttribute("task", task);

        return "task/update";
    }

    @PutMapping
    public String updateTask(@PathVariable("taskId") Long taskId,
                             @Valid TaskRegisterDto task,
                             BindingResult bindingResult) {

        return null;
    }
}
