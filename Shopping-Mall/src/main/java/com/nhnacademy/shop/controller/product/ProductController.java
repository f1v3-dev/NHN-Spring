package com.nhnacademy.shop.controller.product;

import com.nhnacademy.shop.domain.ProductDto;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.repository.ProductRepository;
import com.nhnacademy.shop.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String product(
            @PageableDefault(size = 5, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        Page<ProductDto> productPage = productRepository.getAllBy(pageable);

        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "product/main";
    }
}
