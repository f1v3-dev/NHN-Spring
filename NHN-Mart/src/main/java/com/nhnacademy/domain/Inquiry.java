package com.nhnacademy.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inquiry {

    long id;

    Category category;

    @NotBlank
    String title;

    @NotBlank
    String content;

    LocalDateTime createdDate;

    String writerId;

    boolean isAnswered;

    public Inquiry(Category category, String title, String content, LocalDateTime createdDate, String writerId,
                   boolean isAnswered) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.writerId = writerId;
        this.isAnswered = isAnswered;
    }

    public static Inquiry create(Category category, String title, String content, String writerId) {
        return new Inquiry(category, title, content, LocalDateTime.now(), writerId, false);
    }
}
