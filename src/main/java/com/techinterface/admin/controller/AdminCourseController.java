package com.techinterface.admin.controller;

import com.techinterface.model.dto.CourseDto;
import com.techinterface.model.dto.EditCourseDto;
import com.techinterface.model.request.AddCourseDto;
import com.techinterface.service.CategoryService;
import com.techinterface.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/course")

public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @Value("${message.title}")
    private String titleIsExist;

    @Value("${message.category}")
    private String categoryIsNotExist;

    @Value("${message.id}")
    private String idIsNotExist;

    @GetMapping("")
    public Object getAll() {

        try {

            return new ResponseEntity<Object>(courseService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public Object add(@RequestBody AddCourseDto dto) {
        try {

            if (courseService.checkExistByTitle(dto.getTitle())) {
                return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);
            }

            if (!categoryService.checkExistById(dto.getCategoryId())) {
                return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);

            }

            courseService.add(dto);

            return new ResponseEntity<Object>(HttpStatus.CREATED);

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

    @PutMapping("{id}")
    public Object editCourse(@RequestBody EditCourseDto dto, @PathVariable(name = "id") Integer id) {

        try {

            if (dto.getId() != id) {
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

            }

            if (!courseService.checkExistById(id)) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            }

            if (courseService.checkExistByTitle(dto.getTitle())) {
                return new ResponseEntity<Object>("Title is exist", HttpStatus.BAD_REQUEST);

            }

            if (!categoryService.checkExistById(dto.getCategoryId())) {
                return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);
            }
            courseService.edit(dto);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Integer id) {
        try {

            if (!courseService.checkExistById(id)) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            }

            courseService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

}
