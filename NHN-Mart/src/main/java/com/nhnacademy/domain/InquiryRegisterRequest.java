package com.nhnacademy.domain;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class InquiryRegisterRequest {

    Category category;

    @NotBlank(message = "제목을 입력해주세요")
    String title;

    @NotBlank(message = "내용을 입력해주세요")
    String content;
}
