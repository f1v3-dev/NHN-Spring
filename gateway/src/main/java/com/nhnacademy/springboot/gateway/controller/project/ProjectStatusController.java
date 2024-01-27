package com.nhnacademy.springboot.gateway.controller.project;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.StatusDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/status")
public class ProjectStatusController {

    private final TaskService taskService;

    public ProjectStatusController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getProjectStatus(@PathVariable("projectId") Long projectId,
                                   Model model) {

        model.addAttribute("projectId", projectId);
        return "project/status";
    }

    @PutMapping
    public String editProjectStatus(@PathVariable("projectId") Long projectId,
                                    @ModelAttribute StatusDto status) {

        log.info("status = {}", status);

        CreateResponse response = taskService.editProjectStatus(projectId, status);
        log.info("response = {}", response);

        return "redirect:/project/" + projectId;
    }
}
