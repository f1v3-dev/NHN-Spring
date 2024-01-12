package com.nhnacademy.service;

import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.repository.InquiryRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryServiceImpl(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }


    @Override
    public List<Inquiry> findInquiryListByUserId(String userId) {

        return inquiryRepository.getInquiryList(userId)
                .stream()
                .sorted(Comparator.comparing(Inquiry::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Inquiry findById(Long inquiryId) {
        return inquiryRepository.getInquiry(inquiryId);
    }
}
