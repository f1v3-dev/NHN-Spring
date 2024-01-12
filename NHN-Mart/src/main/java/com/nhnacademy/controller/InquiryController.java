package com.nhnacademy.controller;

import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.User;
import com.nhnacademy.service.InquiryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public String inquiryList(HttpServletRequest request,
                              Model model) {

        User user = (User) request.getSession(false)
                .getAttribute("user");

        model.addAttribute("inquiryList",
                inquiryService.findInquiryListByUserId(user.getId()));

        return "customer/inquiryList";
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String viewInquiry(@PathVariable("inquiryId") Long inquiryId,
                              Model model) {

        model.addAttribute("inquiry", inquiryService.findById(inquiryId));

        return "customer/inquiryView";
    }
}
