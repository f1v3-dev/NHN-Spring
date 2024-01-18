package com.nhnacademy.shop.service.productcategory;

import com.nhnacademy.shop.entity.Product;
import java.util.List;

public interface ProductCategoryService {

    List<Product> findContainsCategory(Integer categoryId);
}
