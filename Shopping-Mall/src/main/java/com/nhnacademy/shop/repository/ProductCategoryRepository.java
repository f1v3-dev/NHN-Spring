package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository
        extends JpaRepository<ProductCategory, ProductCategory.Pk>, ProductCategoryRepositoryCustom {

    void deleteAllByProduct_ProductId(int productId);

    @Query("SELECT COUNT(pc) FROM ProductCategory pc WHERE pc.pk.categoryId = ?1")
    Long countByCategoryId(int categoryId);
}
