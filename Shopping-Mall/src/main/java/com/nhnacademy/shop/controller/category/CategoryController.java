package com.nhnacademy.shop.controller.category;

import com.nhnacademy.shop.entity.Category;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.repository.CategoryRepository;
import com.nhnacademy.shop.service.category.CategoryService;
import com.nhnacademy.shop.service.productcategory.ProductCategoryService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;

    private final ProductCategoryService productCategoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService,
                              ProductCategoryService productCategoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.productCategoryService = productCategoryService;
    }


    @GetMapping
    public String category(
            @PageableDefault(size = 5, sort = "categoryId", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        model.addAttribute("categoryList", categoryPage.getContent());
        model.addAttribute("totalPages", categoryPage.getTotalPages());

        return "category/main";
    }

    @GetMapping("/{categoryId}")
    public String categoryWithProductList(@PathVariable("categoryId") Integer categoryId,
                                         Model model) {

        List<Product> productList = productCategoryService.findContainsCategory(categoryId);
        Category category = categoryService.findByCategoryId(categoryId);

        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        return "category/view";
    }
}
