package com.nhnacademy.springboot.gateway.controller.commnet;

import com.nhnacademy.springboot.gateway.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task/{taskId}/comment/{commentId}/update")
public class CommentUpdateController {

    private final TaskService taskService;

    public CommentUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getCommentUpdateForm(@PathVariable("taskId") Long taskId,
                                       @PathVariable("commentId") Long commentId) {


        return "task/comment/comment-update";
    }

    /**
     *     @PutMapping("/{id}/comment/{commentId}")
     *     public ResponseEntity<CreateResponse> createComment(@PathVariable("id") Long id,
     *                                                         @PathVariable("commentId") Long commentId,
     *                                                         @RequestBody CommentRequest commentRequest) {
     *         Comment comment = commentService.updateComment(id, commentId, commentRequest);
     *         return new ResponseEntity<>(new CreateResponse(comment.getCommentId(), "정상적으로 생성되었습니다"), HttpStatus.CREATED);
     *     }
     */
}
