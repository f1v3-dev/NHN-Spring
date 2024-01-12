package com.nhnacademy.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class InquiryRegisterRequest {

    Category category;

    @NotBlank(message = "제목을 입력해주세요")
    @Size(min = 2, max = 200)
    String title;

    @NotBlank(message = "내용을 입력해주세요")
    @Size(max = 40000)
    String content;
}
