package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c ORDER BY c.categoryId DESC")
    List<Category> findAllCategoriesOrderByCategoryIdDesc();
}
