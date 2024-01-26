package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.TaskRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/task/write")
public class TaskWriteController {


    @GetMapping
    public String getTaskWriteForm(Model model, HttpSession session) {

        log.info("get task write form!!");

        Account account = (Account) session.getAttribute("account");

        model.addAttribute("registrant", account.getUserId());

        return "task/write";
    }

    @PostMapping
    public String writeTask(@Valid TaskRegisterDto task,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        log.info("task : {}", task);

        return "index";
    }


}
