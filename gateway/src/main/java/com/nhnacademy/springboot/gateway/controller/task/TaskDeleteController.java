package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/task/{taskId}/delete")
public class TaskDeleteController {

    private final TaskService taskService;

    public TaskDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping
    public String deleteTask(@PathVariable("projectId") Long projectId,
                             @PathVariable("taskId") Long taskId) {

        log.info("taskId = {}", taskId);

        CreateResponse response = taskService.deleteTask(taskId);
        log.info("response = {}", response);

        return "redirect:/project/" + projectId;

    }
}
