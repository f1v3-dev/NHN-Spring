package com.nhnacademy.shop.service.product;

import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.exception.ProductNotFoundException;
import com.nhnacademy.shop.repository.ProductRepository;
import com.nhnacademy.shop.service.productcategory.ProductCategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryService productCategoryService;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
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

    @Override
    public void deleteBy(Integer productId) {
        productCategoryService.deleteAllBy(productId);
        productRepository.deleteById(productId);
    }
}
