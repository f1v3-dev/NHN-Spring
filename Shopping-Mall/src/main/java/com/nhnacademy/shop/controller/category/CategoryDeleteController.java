package com.nhnacademy.shop.controller.category;

import com.nhnacademy.shop.service.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category/delete")
public class CategoryDeleteController {

    private final CategoryService categoryService;

    public CategoryDeleteController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public String deleteCategory(@RequestParam("categoryId") Integer categoryId) {
        categoryService.deleteBy(categoryId);

        return "redirect:/category";
    }
}
