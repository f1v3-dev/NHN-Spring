package com.nhnacademy.shop.controller.product;

import com.nhnacademy.shop.domain.ProductRegisterRequest;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.exception.ValidationFailedException;
import com.nhnacademy.shop.service.product.ProductService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/product/register")
public class ProductRegisterController {

    private final ProductService productService;

    public ProductRegisterController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productRegisterForm() {
        return "product/register";
    }

    @PostMapping
    public String registerProduct(@Valid ProductRegisterRequest product,
                                  BindingResult bindingResult) {

        log.info("product = {}", product);
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Product savedProduct = productService.save(
                product.getModelNumber(),
                product.getModelName(),
                product.getUnitCost(),
                product.getDescription());

        return "redirect:/product/" + savedProduct.getProductId();
    }
}
