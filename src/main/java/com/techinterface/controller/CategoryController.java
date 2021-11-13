package com.techinterface.controller;

import com.techinterface.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Value("${message.id}")
    private String idIsNotExist;

    @GetMapping("")
    public Object getAll() {
        try {

            return new ResponseEntity<Object>(service.getAllCategory(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public Object getCategory(@PathVariable(name = "id") Integer id) {
        try {

            if (!service.checkExistById(id)) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<Object>(service.getCategoryById(id), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

}
