package com.nhnacademy.shop.service.productcategory;

import com.nhnacademy.shop.domain.ProductDto;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.repository.ProductCategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {


    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> findContainsCategory(Integer categoryId) {
        return productCategoryRepository.findContainsCategory(categoryId);
    }
}
