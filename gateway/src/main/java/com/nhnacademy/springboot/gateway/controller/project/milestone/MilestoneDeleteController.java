package com.nhnacademy.springboot.gateway.controller.project.milestone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}/milestone/delete")
public class MilestoneDeleteController {

    @DeleteMapping("/{milestoneId}")
    public String deleteMilestone(@PathVariable("projectId") Long projectId,
                                  @PathVariable("milestoneId") Long milestoneId) {

        return null;
    }
}
