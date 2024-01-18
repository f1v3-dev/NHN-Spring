package com.nhnacademy.shop.controller.product;

import com.nhnacademy.shop.domain.ProductDto;
import com.nhnacademy.shop.entity.Category;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.service.product.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;


    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public String product(
            @PageableDefault(size = 5, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        Page<ProductDto> productPage = productService.getProducts(pageable);

        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "product/main";
    }

    @GetMapping("/{productId}")
    public String productView(@PathVariable("productId") Integer productId, Model model) {

        List<Category> categoryList = productCategoryService.findCategoriesByProductId(productId);
        Product product = productService.findById(productId);

        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);

        return "product/view";
    }
}
