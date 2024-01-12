package com.nhnacademy.domain;

import lombok.Value;

@Value
public class Inquiry {

    String title;

    Category category;

    String content;

    boolean isAnswered;

}
