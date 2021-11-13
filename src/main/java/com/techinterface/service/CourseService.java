package com.techinterface.service;

import java.util.List;

import com.techinterface.entity.Category;
import com.techinterface.model.dto.CourseDto;
import com.techinterface.model.dto.EditCourseDto;
import com.techinterface.model.request.AddCategoryDto;
import com.techinterface.model.request.AddCourseDto;

public interface CourseService {

    List<CourseDto> findAllByCategory(Integer id);

    List<CourseDto> findAll();

    CourseDto getCourseById(Integer id);

    void add(AddCourseDto dto);

    void edit(EditCourseDto dto);

    void deleteById(Integer id);

    boolean checkExistByTitle(String title);

    boolean checkExistById(Integer id);

    void addUserCourse(Integer courseId, Integer userId);

}
