package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project/{projectId}/tag/delete")
public class TagDeleteController {

    private final TaskService taskService;

    public TagDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping
    public String deleteTagByProjectId(@PathVariable("projectId") Long projectId,
                            @RequestParam Long tagId) {

        taskService.deleteTag(tagId);

        return "redirect:/project/" + projectId + "/tag";
    }


}
