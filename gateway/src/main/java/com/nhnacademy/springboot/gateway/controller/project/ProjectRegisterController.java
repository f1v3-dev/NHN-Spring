package com.nhnacademy.springboot.gateway.controller.project;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/register")
public class ProjectRegisterController {

    private final TaskService taskService;

    public ProjectRegisterController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getProjectRegisterForm(Model model,
                                         HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");
        model.addAttribute("userId", account.getUserId());
        return "project/register";
    }

    @PostMapping
    public String registerProject(@Valid ProjectRegisterRequestDto project,
                                  BindingResult bindingResult) {

        log.info("project = {}", project);

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        CreateResponse createResponse = taskService.registerProject(project);

        log.info("response = {}", createResponse);


        return "redirect:/";
    }
}
