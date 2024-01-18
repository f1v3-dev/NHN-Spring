package com.nhnacademy.shop.controller.category;

import com.nhnacademy.shop.domain.CategoryRegisterRequest;
import com.nhnacademy.shop.exception.ValidationFailedException;
import com.nhnacademy.shop.service.category.CategoryService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/category/register")
public class CategoryRegisterController {

    private final CategoryService categoryService;

    public CategoryRegisterController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryRegisterForm() {

        return "category/register";
    }

    @PostMapping
    public String registerCategory(@Valid CategoryRegisterRequest category,
                                   BindingResult bindingResult) {

        log.error("category = {}", category.getCategoryName());

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        categoryService.save(category.getCategoryName());
        return "redirect:/category/";
    }

}
