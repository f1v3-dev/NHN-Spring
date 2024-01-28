package com.nhnacademy.springboot.gateway.controller.project.milestone;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
// http://localhost:8888/project/10/milestone/delete
@RequestMapping("/project/{projectId}/milestone/delete")
public class MilestoneDeleteController {

    private final TaskService taskService;

    public MilestoneDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping("/{milestoneId}")
    public String deleteMilestone(@PathVariable("projectId") Long projectId,
                                  @PathVariable("milestoneId") Long milestoneId) {

        CreateResponse response = taskService.deleteMilestone(milestoneId);
        log.info("response = {}", response);

        return "redirect:/project/" + projectId + "/milestone";
    }
}
