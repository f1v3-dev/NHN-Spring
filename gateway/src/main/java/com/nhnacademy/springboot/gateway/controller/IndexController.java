package com.nhnacademy.springboot.gateway.controller;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getIndex(Model model,
                           HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");

        List<ProjectListRequestDto> projectList = taskService.getProjectList(account.getUserId());

        model.addAttribute("projectList", projectList);
        return "index";
    }

}
