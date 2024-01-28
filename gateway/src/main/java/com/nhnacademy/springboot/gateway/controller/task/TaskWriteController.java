package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/task/write/{projectId}")
public class TaskWriteController {

    private final TaskService taskService;

    public TaskWriteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTaskWriteForm(@PathVariable("projectId") String projectId,
                                   Model model,
                                   HttpSession session) {

        Long id = Long.valueOf(projectId);

        Account account = (Account) session.getAttribute("account");
        List<TagListModuleResponse> tagList = taskService.getTagList(id);
        List<MilestoneDto> milestoneList = taskService.getMilestoneList(id);

        model.addAttribute("registrant", account.getUserId());
        model.addAttribute("tagList", tagList);
        model.addAttribute("milestoneList", milestoneList);

        return "task/write";
    }

    @PostMapping
    public String writeTask(@Valid TaskRegisterDto task,
                            BindingResult bindingResult,
                            @PathVariable("projectId") Long projectId) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        log.info("task.milestoneId = {}", task.getMilestoneId());


        task.setProjectId(projectId);



        log.info("task : {}", task);

        CreateResponse response = taskService.registerTask(task);

        log.info("response : {}", response);

        return "redirect:/project/" + projectId;
    }


}
