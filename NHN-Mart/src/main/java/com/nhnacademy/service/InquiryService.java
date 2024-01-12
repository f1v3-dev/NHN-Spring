package com.nhnacademy.service;

import com.nhnacademy.domain.Inquiry;
import java.util.List;

public interface InquiryService {

    List<Inquiry> findInquiryListByUserId(String userId);

    Inquiry findById(Long inquiryId);

    boolean isExists(long id);

    List<Inquiry> findInquiryListByUserIdAndCategory(String id, String category);

    List<Inquiry> findNotAnsweredInquiryList();

    void answer(Long inquiryId, String answer, String answerWriterName);
}
