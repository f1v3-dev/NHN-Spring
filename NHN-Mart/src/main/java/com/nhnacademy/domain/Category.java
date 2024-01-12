package com.nhnacademy.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    COMPLAINT("불만 접수"),
    SUGGESTION("제안"),
    REFUND_EXCHANGE("환불/교환"),
    PRAISE("칭찬해요"),
    ETC("기타 문의");

    private final String categoryName;
}
