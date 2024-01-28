package com.nhnacademy.springboot.gateway.controller.project.tag;

import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/tag")
public class TagListController {

    private final TaskService taskService;

    public TagListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTagListByProjectId(@PathVariable("projectId") Long projectId,
                                        Model model) {

        List<TagListModuleResponse> tagList = taskService.getTagList(projectId);

        log.info("tagList = {}", tagList);

        model.addAttribute("tagList", tagList);

        return "project/tag/tag-list";
    }
}
