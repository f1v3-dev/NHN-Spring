package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/tag/delete")
public class TagDeleteController {

    private final TaskService taskService;

    public TagDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping("/{tagId}")
    public String deleteTagByProjectId(@PathVariable("projectId") Long projectId,
                                       @PathVariable("tagId") Long tagId) {

        log.info("projectId = {}", projectId);
        log.info("tagId = {}", tagId);

        CreateResponse response = taskService.deleteTag(projectId, tagId);


        return "redirect:/project/" + projectId + "/tag";
    }


}
