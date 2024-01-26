package com.nhnacademy.springboot.gateway.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    @GetMapping
    public String getTaskList() {
        return "task/main";
    }
}
