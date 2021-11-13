package com.techinterface.repository;

import java.util.List;

import com.techinterface.entity.Category;
import com.techinterface.entity.Course;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    public List<Course> findByCategory(Category category);

    public Course findByTitle(String title);

}
