package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategory.Pk> {


    void deleteAllByProduct_ProductId(int productId);

    Long countByProduct_ProductId(int productId);

    List<ProductCategory> findAllByProduct_ProductId(int productId);

    Long countByCategory_CategoryId(int categoryId);
}
