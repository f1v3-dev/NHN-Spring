package com.nhnacademy.springboot.gateway.controller.commnet;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.service.TaskService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/task/{taskId}/comment/{commentId}/update")
public class CommentUpdateController {

    private final TaskService taskService;

    public CommentUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getCommentUpdateForm(@PathVariable("taskId") Long taskId,
                                       @PathVariable("commentId") Long commentId,
                                       Model model,
                                       HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");

        model.addAttribute("registrant", account.getUserId());
        model.addAttribute("taskId", taskId);
        model.addAttribute("commentId", commentId);

        return "task/comment/comment-update";
    }


    @PutMapping
    public String updateComment(@PathVariable("taskId") Long taskId,
                                @PathVariable("commentId") Long commentId,
                                @Valid CommentRequest commentRequest) {

        log.info("commentRequest = {}", commentRequest);

        CreateResponse response = taskService.updateComment(taskId, commentId, commentRequest);
        log.info("response = {}", response);

        return "redirect:/task/" + taskId;
    }
}
