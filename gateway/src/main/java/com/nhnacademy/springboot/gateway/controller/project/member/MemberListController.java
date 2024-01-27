package com.nhnacademy.springboot.gateway.controller.project.member;

import com.nhnacademy.springboot.gateway.domain.task.member.ProjectMemberListResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}/member")
public class MemberListController {

    private final TaskService taskService;

    public MemberListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getProjectMemberList(@PathVariable("projectId") Long projectId,
                                       Model model) {

        List<ProjectMemberListResponse> memberList = taskService.getMemberListByProjectId(projectId);
        model.addAttribute("memberList", memberList);

        return "project/member/member-list";

    }
}
