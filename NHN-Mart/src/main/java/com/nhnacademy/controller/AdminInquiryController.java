package com.nhnacademy.controller;

import com.nhnacademy.domain.AnswerInquiry;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.InquiryNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.InquiryService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminInquiryController {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    private final InquiryService inquiryService;



    public AdminInquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/cs")
    public String adminInquiry(Model model) {
        List<Inquiry> inquiryList = inquiryService.findNotAnsweredInquiryList();

        model.addAttribute("inquiryList", inquiryList);

        return "admin/inquiryList";
    }

    @GetMapping("/cs/inquiry/{inquiryId}")
    public String answerInquiry(@PathVariable("inquiryId") Long inquiryId,
                                Model model) {

        if (!inquiryService.isExists(inquiryId)) {
            throw new InquiryNotFoundException();
        }

        model.addAttribute("inquiry", inquiryService.findById(inquiryId));

        File file = new File(UPLOAD_DIR);
        List<String> fileList = Arrays.stream(file.listFiles())
                .filter(f -> f.getName().startsWith(inquiryId + "_"))
                .map(f -> f.getName().substring(f.getName().indexOf("_") + 1))
                .collect(Collectors.toList());

        model.addAttribute("fileList", fileList);

        return "admin/inquiryAnswerForm";
    }

    @PutMapping("/cs/inquiry/{inquiryId}")
    public String answerInquiry(@PathVariable("inquiryId") Long inquiryId,
                                @Valid AnswerInquiry answer,
                                BindingResult bindingResult,
                                HttpSession session) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!inquiryService.isExists(inquiryId)) {
            throw new InquiryNotFoundException();
        }

        User user = (User) session.getAttribute("user");
        inquiryService.answer(inquiryId, answer.getAnswer(), user.getName());

        return "redirect:/admin/cs";
    }
}
