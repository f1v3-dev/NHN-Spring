package com.nhnacademy.repository;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InquiryRepository {

    Inquiry addInquiry(Category category, String title, String content, String writerId);

    List<Inquiry> getInquiryList(String writerId);

    Inquiry getInquiry(long id);

    boolean exists(long id);

    Map<Long, Inquiry> getAllInquiry();

    void answer(Long inquiryId, String answer, String answerWriterName);
}
