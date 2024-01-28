package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


        CreateResponse response = taskService.deleteTag(projectId, tagId);
        log.info("response = {}", response);
        return "redirect:/project/" + projectId + "/tag";
    }


}
