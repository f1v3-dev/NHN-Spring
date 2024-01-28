package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/tag/update")
public class TagUpdateController {

    private final TaskService taskService;

    public TagUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTagUpdateFormByProjectId(@PathVariable("projectId") Long projectId,
                                              @ModelAttribute TagListModuleResponse tag,
                                              Model model) {

        model.addAttribute("projectId", projectId);
        model.addAttribute("tag", tag);

        log.info("projectId = {}", projectId);
        log.info("tag = {}", tag);

        return "project/tag/tag-update";
    }

    @PutMapping
    public String updateTagByProjectId(@PathVariable("projectId") Long projectId,
                                       @RequestParam("tagId") Long tagId,
                                       @RequestParam("tagName") String tagName) {

        log.info("projectId = {}", projectId);
        log.info("tagId = {}", tagId);
        log.info("tagName = {}", tagName);


        // projectId + tagName을 가진 객체 + tagId
        CreateResponse response = taskService.updateTag(tagId,
                new TagRequestDto(projectId, tagName));

        log.info("response = {}", response);

        return "redirect:/project/" + projectId + "/tag";
    }

}
