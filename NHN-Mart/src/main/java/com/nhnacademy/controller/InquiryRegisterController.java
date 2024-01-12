package com.nhnacademy.controller;

import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.InquiryRegisterRequest;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.repository.InquiryRepository;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class InquiryRegisterController {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;
    private final InquiryRepository inquiryRepository;

    public InquiryRegisterController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }


    @GetMapping("/cs/inquiry")
    public String inquiry() {
        return "customer/inquiryForm";
    }

    @PostMapping("/cs/inquiry")
    public String inquiryRegister(@Valid InquiryRegisterRequest inquiryRequest,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  @RequestParam("fileList") List<MultipartFile> fileList) throws IOException {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        User user = (User) session.getAttribute("user");

        Inquiry inquiry = inquiryRepository.addInquiry(inquiryRequest.getCategory(),
                inquiryRequest.getTitle(),
                inquiryRequest.getContent(),
                user.getId());

        if (!fileList.get(0).isEmpty()) {
            for (MultipartFile file : fileList) {
                log.info("multipartFile: {}", file.getOriginalFilename());
                file.transferTo(Paths.get(UPLOAD_DIR + inquiry.getId() + "_" + file.getOriginalFilename()));
            }
        }

        return "redirect:/cs";
    }

}
