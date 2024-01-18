package com.nhnacademy.shop.service.product;

import com.nhnacademy.shop.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product save(String modelNumber, String modelName, Integer unitCost, String description);

    Product findById(Integer productId);
}
