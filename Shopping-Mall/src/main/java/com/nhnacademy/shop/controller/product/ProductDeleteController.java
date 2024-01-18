package com.nhnacademy.shop.controller.product;

import com.nhnacademy.shop.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product/delete")
public class ProductDeleteController {

    private final ProductService productService;


    public ProductDeleteController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String deleteProduct(@RequestParam("productId") Integer productId) {
        productService.deleteBy(productId);

        return "redirect:/product";
    }
}
