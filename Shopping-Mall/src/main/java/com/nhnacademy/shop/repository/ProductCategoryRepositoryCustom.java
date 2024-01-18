package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Product;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductCategoryRepositoryCustom {

    List<Product> findContainsCategory(Integer categoryId);

}
