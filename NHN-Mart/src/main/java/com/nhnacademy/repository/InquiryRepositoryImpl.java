package com.nhnacademy.repository;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private final Map<Long, Inquiry> inquiryMap = new HashMap<>();

    @Override
    public Inquiry addInquiry(Category category, String title, String content, String writerId) {

        long id = inquiryMap.keySet().stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Inquiry inquiry = Inquiry.create(category, title, content, writerId);
        inquiry.setId(id);

        inquiryMap.put(id, inquiry);

        return inquiry;
    }

    @Override
    public List<Inquiry> getInquiryList(String writerId) {
        return new ArrayList<>(inquiryMap.values());
    }

    @Override
    public Inquiry getInquiry(long id) {

        return exists(id) ? inquiryMap.get(id) : null;
    }

    @Override
    public boolean exists(long id) {
        return inquiryMap.containsKey(id);
    }

    @Override
    public Map<Long, Inquiry> getAllInquiry() {
        return inquiryMap;
    }

    @Override
    public void answer(Long inquiryId, String answer, String answerWriterName) {
        Inquiry inquiry = getInquiry(inquiryId);

        inquiry.setAnswered(true);
        inquiry.setAnswerContent(answer);
        inquiry.setAnswerWriterName(answerWriterName);
        inquiry.setAnsweredDate(LocalDateTime.now());
    }
}
