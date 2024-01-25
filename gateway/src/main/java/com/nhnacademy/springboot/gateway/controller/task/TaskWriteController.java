package com.nhnacademy.springboot.gateway.controller.task;

import com.nhnacademy.springboot.gateway.domain.account.AccountRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.Tag;
import com.nhnacademy.springboot.gateway.domain.task.TaskRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import java.time.LocalDate;
import java.util.List;
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

        AccountRequestDto account = (AccountRequestDto) session.getAttribute("account");

        model.addAttribute("milestoneList",
                List.of(new Milestone(1L, 1L, "milestone1", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 31)),
                        new Milestone(2L, 1L, "milestone2", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))));

        model.addAttribute("tagList", List.of(new Tag(1L, "tag1", 1L), new Tag(2L, "tag2", 1L)));

        model.addAttribute("accountId", account.getUserId());

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
