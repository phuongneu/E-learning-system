package com.techinterface.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techinterface.entity.Category;
import com.techinterface.entity.Course;
import com.techinterface.entity.User;
import com.techinterface.model.dto.CourseDto;
import com.techinterface.model.dto.EditCourseDto;
import com.techinterface.model.request.AddCourseDto;
import com.techinterface.repository.CategoryRepository;
import com.techinterface.repository.CourseRepository;
import com.techinterface.repository.UserRepository;
import com.techinterface.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CourseDto> findAllByCategory(Integer id) {
        Category category = categoryRepository.findById(id).get();
        List<Course> courses = courseRepository.findByCategory(category);
        List<CourseDto> dto = new ArrayList<>();
        for (Course course : courses) {
            dto.add(new CourseDto(course.getId(), course.getTitle(), course.getLeturesCount(), course.getHourCount(),
                    course.getPrice(), course.getPromotionPrice(), course.getContent()));

        }

        return dto;

    }

    @Override
    public void add(AddCourseDto dto) {

        Course course = new Course(dto.getTitle(), dto.getLeturesCount(), dto.getHourCount(), dto.getPrice(),
                dto.getPromotionPrice(), dto.getContent());

        Category category = new Category(dto.getCategoryId());
        course.setCategory(category);
        courseRepository.save(course);

    }

    @Override
    public void edit(EditCourseDto dto) {
        Course course = courseRepository.findById(dto.getId()).get();
        course.setTitle(dto.getTitle());
        course.setLeturesCount(dto.getLeturesCount());
        course.setHourCount(dto.getHourCount());
        course.setPrice(dto.getPrice());
        course.setPromotionPrice(dto.getPromotionPrice());
        course.setContent(dto.getContent());
        courseRepository.save(course);

    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);

    }

    @Override
    public boolean checkExistByTitle(String title) {

        Course course = courseRepository.findByTitle(title);

        if (course == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkExistById(Integer id) {

        Course course = courseRepository.findById(id).get();
        if (course == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = (List<Course>) courseRepository.findAll();

        List<CourseDto> dto = new ArrayList<>();
        for (Course course : courses) {
            dto.add(new CourseDto(course.getId(), course.getTitle(), course.getLeturesCount(), course.getHourCount(),
                    course.getPrice(), course.getPromotionPrice(), course.getContent()));
        }
        return dto;
    }

    @Override
    public CourseDto getCourseById(Integer id) {
        Course course = courseRepository.findById(id).get();

        CourseDto dto = new CourseDto(course.getId(), course.getTitle(), course.getLeturesCount(),
                course.getHourCount(), course.getPrice(), course.getPromotionPrice(), course.getContent());
        return dto;
    }

    @Override
    public void addUserCourse(Integer courseId, Integer userId) {

        User user = userRepository.findById(userId).get();
        Course course = courseRepository.findById(courseId).get();

        user.addCourse(course);
        userRepository.save(user);

    }

}
