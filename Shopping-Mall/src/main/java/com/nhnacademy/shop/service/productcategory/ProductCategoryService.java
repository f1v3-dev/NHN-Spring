package com.nhnacademy.shop.service.productcategory;

import com.nhnacademy.shop.entity.Category;
import com.nhnacademy.shop.entity.Product;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryService {

    List<Product> findContainsCategory(Integer categoryId);

    List<Category> findCategoriesByProductId(Integer productId);

    @Transactional
    void deleteAllBy(Integer productId);
}
