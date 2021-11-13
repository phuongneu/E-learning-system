package com.techinterface.repository;

import com.techinterface.entity.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Category findByTitle(String title);
}
