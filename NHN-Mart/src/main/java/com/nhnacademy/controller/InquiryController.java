package com.nhnacademy.controller;

import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.InquiryNotFoundException;
import com.nhnacademy.service.InquiryService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs")
public class InquiryController {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public String inquiryList(@RequestParam(name = "category", required = false) String category,
                              HttpSession session,
                              Model model) {

        User user = (User) session.getAttribute("user");

        List<Inquiry> inquiryList;
        if (Objects.nonNull(category)) {
            inquiryList = inquiryService.findInquiryListByUserIdAndCategory(user.getId(), category);
        } else {
            inquiryList = inquiryService.findInquiryListByUserId(user.getId());
        }

        model.addAttribute("inquiryList", inquiryList);
        return "customer/inquiryList";
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String viewInquiry(@PathVariable("inquiryId") Long inquiryId,
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

        return "customer/inquiryView";
    }
}
