package com.nhnacademy.shop.service.category;

import com.nhnacademy.shop.entity.Category;
import com.nhnacademy.shop.exception.CategoryNotFoundException;
import com.nhnacademy.shop.exception.CategoryUsedProductException;
import com.nhnacademy.shop.repository.CategoryRepository;
import com.nhnacademy.shop.repository.ProductCategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductCategoryRepository productCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesOrderByCategoryIdDesc() {
        return categoryRepository.findAllCategoriesOrderByCategoryIdDesc();
    }


    @Override
    public Category findByCategoryId(Integer categoryId) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }

        throw new CategoryNotFoundException();
    }

    @Override
    public void deleteBy(Integer categoryId) {
        if (productCategoryRepository.countByCategoryId(categoryId) > 0) {
            throw new CategoryUsedProductException();
        }

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category save(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);

        return categoryRepository.save(category);
    }
}
