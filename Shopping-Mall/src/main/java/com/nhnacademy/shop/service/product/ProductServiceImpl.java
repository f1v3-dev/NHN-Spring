package com.nhnacademy.shop.service.product;

import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.exception.ProductNotFoundException;
import com.nhnacademy.shop.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Product save(String modelNumber, String modelName, Integer unitCost, String description) {
        Product product = new Product();
        product.setModelNumber(modelNumber);
        product.setModelName(modelName);
        product.setUnitCost(unitCost);
        product.setDescription(description);

        return productRepository.save(product);
    }

    @Override
    public Product findById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        throw new ProductNotFoundException();
    }
}
