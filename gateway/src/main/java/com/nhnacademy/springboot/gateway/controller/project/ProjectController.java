package com.nhnacademy.springboot.gateway.controller.project;

import com.nhnacademy.springboot.gateway.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/project/{projectId}")
public class ProjectController {

    private final TaskService taskService;

    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getProject(@PathVariable("projectId") Long projectId) {

        // getProject -> 이 projectId를 참조하고있는 모든 task를 던져줌
        // ex. Project Dto + List<Task> taskListByProjectId

//        taskService.getProject(projectId);


        return null;
    }
}
