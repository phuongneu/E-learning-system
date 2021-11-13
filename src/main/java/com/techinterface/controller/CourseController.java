package com.techinterface.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.techinterface.entity.User;
import com.techinterface.repository.UserRepository;
import com.techinterface.service.CategoryService;
import com.techinterface.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Value("${message.id}")
    private String idIsNotExist;

    @Value("${message.category}")
    private String categoryIsNotExist;

    @GetMapping("category/{id}")
    public Object getCourseByCategory(@PathVariable(name = "id") Integer categoryId) {

        try {

            if (!categoryService.checkExistById(categoryId)) {
                return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);

            }

            return new ResponseEntity<Object>(courseService.findAllByCategory(categoryId), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("{id}")
    public Object getCourseById(@PathVariable(name = "id") Integer id) {

        try {

            if (!courseService.checkExistById(id)) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            }

            return new ResponseEntity<Object>(courseService.getCourseById(id), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("{id}")
    public Object addUserCourse(@PathVariable(name = "id") Integer courseId, HttpServletRequest request) {

        try {

            if (!courseService.checkExistById(courseId)) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            }

            Principal principal = request.getUserPrincipal();
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            if (user != null) {
                courseService.addUserCourse(courseId, user.getId());
            }

            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

}
