package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


    Long countByCategoryId(int categoryId);

    Long countByCategoryName(String categoryName);

}
