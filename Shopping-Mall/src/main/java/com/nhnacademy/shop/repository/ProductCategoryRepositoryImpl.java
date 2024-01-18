package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Product;
import com.nhnacademy.shop.entity.ProductCategory;
import com.nhnacademy.shop.entity.QCategory;
import com.nhnacademy.shop.entity.QProduct;
import com.nhnacademy.shop.entity.QProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductCategoryRepositoryImpl extends QuerydslRepositorySupport
        implements ProductCategoryRepositoryCustom {

    public ProductCategoryRepositoryImpl() {
        super(ProductCategory.class);
    }

    @Override
    public List<Product> findContainsCategory(Integer categoryId) {
        QCategory category = QCategory.category;
        QProductCategory productCategory = QProductCategory.productCategory;
        QProduct product = QProduct.product;

        return from(productCategory)
                .innerJoin(productCategory.category, category)
                .innerJoin(productCategory.product, product)
                .where(category.categoryId.eq(categoryId))
                .select(product)
                .fetch();
    }
}
