package com.nhnacademy.controller;

import com.nhnacademy.domain.InquiryRegisterRequest;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.repository.InquiryRepository;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class InquiryRegisterController {
    private static final String UPLOAD_DIR = "/Users/seungjo/Downloads/";
    private final InquiryRepository inquiryRepository;

    public InquiryRegisterController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }


    @GetMapping("/cs/inquiry")
    public String inquiry() {
        return "customer/inquiryForm";
    }

    // @RequestParam("fileList") List<MultipartFile> fileList
    @PostMapping("/cs/inquiry")
    public String inquiryRegister(@Valid InquiryRegisterRequest inquiryRequest,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) {

        log.info("inquiryRegisterRequest = {}", inquiryRequest);

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        User user = (User) request.getSession(false).getAttribute("user");

        inquiryRepository.addInquiry(inquiryRequest.getCategory(),
                inquiryRequest.getTitle(),
                inquiryRequest.getContent(),
                user.getId());

        return "redirect:/cs";
    }

}
