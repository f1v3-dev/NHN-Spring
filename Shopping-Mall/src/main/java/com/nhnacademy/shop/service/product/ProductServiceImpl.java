package com.nhnacademy.shop.service.product;

import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
