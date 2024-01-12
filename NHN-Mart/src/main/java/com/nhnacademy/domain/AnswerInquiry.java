package com.nhnacademy.domain;

import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class AnswerInquiry {

    @Size(min = 1, max = 1000)
    String answer;
}
