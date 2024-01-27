package com.nhnacademy.springboot.gateway.controller.project.milestone;

import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}/milestone")
public class MilestoneListController {

    private final TaskService taskService;

    public MilestoneListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getMilestoneListByProjectId(@PathVariable("projectId") Long projectId,
                                              Model model) {

        List<Milestone> milestoneList =
                taskService.getMilestoneList(projectId).stream().
                        map(MilestoneDto::toEntity).
                        collect(Collectors.toList());

        model.addAttribute("milestoneList", milestoneList);

        return "project/milestone/milestone-list";
    }
}
