package com.nhnacademy.springboot.gateway.controller.project.member;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponseString;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/member/delete")
public class MemberDeleteController {

    private final TaskService taskService;

    public MemberDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * <form th:action="@{'/project/' + ${projectId} + '/member/delete'}" method="post">
     * <input type="hidden" name="_method" value="delete"/>
     * <input type="hidden" name="userId" th:value="${userId}"/>
     * <button type="submit" class="tag-action-link" style="background-color: #1F4E8C;">삭제</button>
     * </form>
     */

    @DeleteMapping
    public String deleteMemberFromProject(@PathVariable("projectId") Long projectId,
                                          UserDto userDto) {

        log.info("projectId = {}", projectId);
        log.info("userDto = {}", userDto);

        CreateResponseString response = taskService.deleteUserFromProject(projectId, userDto);

        log.info("response = {}", response);

        return "redirect:/project/" + projectId + "/member";
    }
}
