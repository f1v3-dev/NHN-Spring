package com.nhnacademy.shop.service.productcategory;

import com.nhnacademy.shop.entity.Category;
import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.repository.ProductCategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Category> findCategoriesByProductId(Integer productId) {
        return productCategoryRepository.findCategoriesByProductId(productId);
    }

    @Override
    @Transactional
    public void deleteAllBy(Integer productId) {
        productCategoryRepository.deleteAllByProduct_ProductId(productId);
    }
}
