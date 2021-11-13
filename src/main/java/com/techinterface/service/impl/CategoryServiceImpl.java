package com.techinterface.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techinterface.entity.Category;
import com.techinterface.model.dto.CategoryDto;
import com.techinterface.model.request.AddCategoryDto;
import com.techinterface.repository.CategoryRepository;
import com.techinterface.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> listCategories = (List<Category>) categoryRepository.findAll();
        List<CategoryDto> dto = new ArrayList<>();

        for (Category category : listCategories) {
            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getTitle());
            dto.add(categoryDto);
        }

        return dto;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        CategoryDto dto = new CategoryDto(category.getId(), category.getTitle());

        return dto;
    }

    @Override
    public void add(AddCategoryDto dto) {
        Category category = new Category(dto.getTitle());
        categoryRepository.save(category);
    }

    @Override
    public void edit(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getId()).get();
        category.setTitle(dto.getTitle());
        categoryRepository.save(category);

    }

    @Override
    public boolean checkExistByTitle(String title) {

        Category category = categoryRepository.findByTitle(title);

        if (category == null) {
            return false;
        }

        return true;

    }

    @Override
    public boolean checkExistById(Integer id) {
        return categoryRepository.findById(id).isPresent();
    }

}
