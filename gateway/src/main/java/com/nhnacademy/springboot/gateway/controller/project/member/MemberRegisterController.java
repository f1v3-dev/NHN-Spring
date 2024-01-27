package com.nhnacademy.springboot.gateway.controller.project.member;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponseString;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.AccountService;
import com.nhnacademy.springboot.gateway.service.TaskService;
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
@RequestMapping("/project/{projectId}/member/register")
public class MemberRegisterController {

    private final AccountService accountService;
    private final TaskService taskService;

    public MemberRegisterController(AccountService accountService, TaskService taskService) {
        this.accountService = accountService;
        this.taskService = taskService;
    }

    @GetMapping
    public String getMemberRegisterForm(@PathVariable("projectId") Long projectId,
                                        Model model) {

        model.addAttribute("projectId", projectId);
        return "project/member/member-register";
    }

    @PostMapping
    public String registerMember(@PathVariable("projectId") Long projectId,
                                 @Valid UserDto user,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        CreateResponseString response = taskService.registerMember(projectId, user);

        log.info("response : {}", response);


        return "redirect:/project/" + projectId + "/member";
    }
}
