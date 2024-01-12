package com.nhnacademy.service;

import com.nhnacademy.domain.Inquiry;
import java.util.List;

public interface InquiryService {

    List<Inquiry> findInquiryListByUserId(String userId);

    Inquiry findById(Long inquiryId);
}
