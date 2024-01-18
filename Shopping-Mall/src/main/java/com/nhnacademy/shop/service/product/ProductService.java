package com.nhnacademy.shop.service.product;

import com.nhnacademy.shop.domain.ProductDto;
import com.nhnacademy.shop.entity.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    List<Product> findAllProducts();

    Page<ProductDto> getProducts(Pageable pageable);

    Product save(String modelNumber, String modelName, Integer unitCost, String description);

    Product findById(Integer productId);

    void deleteBy(Integer productId);
}
