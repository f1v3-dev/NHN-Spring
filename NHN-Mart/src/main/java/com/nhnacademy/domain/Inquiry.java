package com.nhnacademy.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Inquiry {

    @Setter
    long id;

    Category category;

    @NotBlank
    @Size(min = 2, max = 200)
    String title;

    @NotBlank
    @Size(max = 40000)
    String content;

    LocalDateTime createdDate;

    String writerId;

    @Setter
    boolean isAnswered;

    @Setter
    LocalDateTime answeredDate;

    @Setter
    String answerContent;

    @Setter
    String answerWriterName;

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
