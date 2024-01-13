package com.nhnacademy.service;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.repository.InquiryRepository;
import java.util.ArrayList;
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

    @Override
    public boolean isExists(long id) {
        return inquiryRepository.exists(id);
    }

    @Override
    public List<Inquiry> findInquiryListByUserIdAndCategory(String id, String category) {
        return findInquiryListByUserId(id)
                .stream()
                .filter(inquiry -> inquiry.getCategory().name().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Inquiry> findNotAnsweredInquiryList() {
        return inquiryRepository.getAllInquiry().values()
                .stream()
                .filter(inquiry -> !inquiry.isAnswered())
                .sorted(Comparator.comparing(Inquiry::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void answer(Long inquiryId, String answer, String answerWriterName) {
        inquiryRepository.answer(inquiryId, answer, answerWriterName);
    }

    @Override
    public Inquiry addInquiry(Category category, String title, String content, String id) {
        return inquiryRepository.addInquiry(category, title, content, id);
    }
}
