package com.techinterface.service;

import java.util.List;

import com.techinterface.model.dto.CategoryDto;
import com.techinterface.model.request.AddCategoryDto;

public interface CategoryService {

    void deleteById(Integer id);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(Integer id);

    void add(AddCategoryDto dto);

    void edit(CategoryDto dto);

    boolean checkExistByTitle(String title);

    boolean checkExistById(Integer id);

}
