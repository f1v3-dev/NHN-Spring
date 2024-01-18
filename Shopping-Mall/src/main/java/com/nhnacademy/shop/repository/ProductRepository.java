package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.domain.ProductDto;
import com.nhnacademy.shop.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Long countByModelNumber(String modelNumber);

    Optional<Product> findByModelNumber(String modelNumber);


    List<Product> findByModelNameLike(String modelName);

    Long countByModelNameLike(String modelName);

    Page<ProductDto> getAllBy(Pageable pageable);
}
