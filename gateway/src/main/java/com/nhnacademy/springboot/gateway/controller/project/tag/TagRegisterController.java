package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/tag/register")
public class TagRegisterController {

    private final TaskService taskService;

    public TagRegisterController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTagRegisterForm(@PathVariable("projectId") Long projectId,
                                     Model model) {

        model.addAttribute("projectId", projectId);
        return "project/tag/tag-register";
    }

    @PostMapping
    public String registerTagByProjectId(@PathVariable("projectId") Long projectId,
                              String tagName) {


        CreateResponse createResponse = taskService.registerTag(new TagRequestDto(projectId, tagName));
        log.info("createResponse: {}", createResponse);

        return "redirect:/project/" + projectId + "/tag";
    }
}
