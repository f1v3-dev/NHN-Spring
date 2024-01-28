package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/{projectId}/task/{taskId}/update")
public class TaskUpdateController {

    private final TaskService taskService;

    public TaskUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTaskUpdateForm(@PathVariable("taskId") Long taskId,
                                    @PathVariable("projectId") String projectId,
                                    Model model) {

        Long id = Long.valueOf(projectId);

        TaskModuleResponse task = taskService.getTask(taskId);
        List<TagListModuleResponse> tagList = taskService.getTagList(id);
        List<MilestoneDto> milestoneList = taskService.getMilestoneList(id);

        model.addAttribute("tagList", tagList);
        model.addAttribute("milestoneList", milestoneList);
        model.addAttribute("task", task);
        model.addAttribute("projectId", projectId);

        return "task/update";
    }

    @PutMapping
    public String updateTask(@PathVariable("taskId") Long taskId,
                             @Valid TaskRegisterDto task,
                             BindingResult bindingResult,
                             @PathVariable("projectId") Long projectId) {


        task.setProjectId(projectId);

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        CreateResponse response = taskService.updateTask(taskId, task);
        log.info("response : {}", response);

        return "redirect:/project/" + projectId + "/task/" + taskId;
    }
}
