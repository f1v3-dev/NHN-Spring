package com.nhnacademy.shop.service.category;

import com.nhnacademy.shop.entity.Category;
import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();

    List<Category> findAllCategoriesOrderByCategoryIdDesc();

    Category findByCategoryId(Integer categoryId);

    void deleteBy(Integer categoryId);

    Category save(String categoryName);
}
