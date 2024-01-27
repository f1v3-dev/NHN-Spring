package com.nhnacademy.springboot.gateway.controller.project;

import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}")
public class ProjectController {

    private final TaskService taskService;

    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getProject(@PathVariable("projectId") Long projectId,
                             Model model) {

        TaskListResponse taskList = taskService.getTaskList(projectId);

        log.info("taskList: {}", taskList);
        log.info("projectId: {}", projectId);

        model.addAttribute("projectId", projectId);
        model.addAttribute("taskList", taskList);


        return "project/main";
    }
}
