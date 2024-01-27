package com.nhnacademy.springboot.gateway.controller.project.milestone;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRequest;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/milestone/register")
public class MilestoneRegisterController {

    private final TaskService taskService;

    public MilestoneRegisterController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getMilestoneRegisterForm(@PathVariable("projectId") Long projectId,
                                           Model model) {

        model.addAttribute("projectId", projectId);

        return "project/milestone/milestone-register";
    }

    @PostMapping
    public String registerMilestone(@PathVariable("projectId") Long projectId,
                                    @Valid MilestoneRegisterDto milestone,
                                    BindingResult bindingResult) {

        milestone.setProjectId(projectId);
        log.info("milestone = {}", milestone);

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }


        CreateResponse response = taskService.registerMilestone(milestone);

        log.info("response = {}", response);

        return "redirect:/project/" + projectId + "/milestone";
    }
}
