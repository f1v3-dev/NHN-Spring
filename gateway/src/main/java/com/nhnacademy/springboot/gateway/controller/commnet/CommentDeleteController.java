package com.nhnacademy.springboot.gateway.controller.commnet;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/task/{taskId}/comment/{commentId}/delete")
public class CommentDeleteController {

    private final TaskService taskService;

    public CommentDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping
    public String deleteComment(@PathVariable("taskId") Long taskId,
                                @PathVariable("commentId") Long commentId) {


        CreateResponse response = taskService.deleteComment(taskId, commentId);
        log.info("response = {}", response);

        return "redirect:/task/" + taskId;
    }

}
