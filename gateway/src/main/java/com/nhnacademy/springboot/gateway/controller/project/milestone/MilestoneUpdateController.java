package com.nhnacademy.springboot.gateway.controller.project.milestone;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/milestone/update")
public class MilestoneUpdateController {

    private final TaskService taskService;

    public MilestoneUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{milestoneId}")
    public String getMilestoneUpdateFormByProjectId(@PathVariable("projectId") Long projectId,
                                                    @PathVariable("milestoneId") Long milestoneId,
                                                    Model model) {


        MilestoneDto milestoneDto = taskService.getMilestone(milestoneId);
        Milestone milestone = milestoneDto.toEntity();

        log.info("milestone : {}", milestone);

        model.addAttribute("projectId", projectId);
        model.addAttribute("milestone", milestone);

        return "project/milestone/milestone-update";
    }

    @PutMapping
    public String updateMilestone(@PathVariable("projectId") Long projectId,
                                  @RequestParam("milestoneId") Long milestoneId,
                                  @Valid MilestoneRegisterDto milestone,
                                  BindingResult bindingResult) {

        milestone.setProjectId(projectId);

        log.info("milestone = {}", milestone);


        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        CreateResponse response = taskService.updateMilestone(milestoneId, milestone);

        log.info("response = {}", response);

        return "redirect:/project/" + projectId + "/milestone";
    }
}
