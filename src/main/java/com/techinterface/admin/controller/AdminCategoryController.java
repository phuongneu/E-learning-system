package com.techinterface.admin.controller;

import org.springframework.web.bind.annotation.RestController;

import com.techinterface.model.dto.CategoryDto;
import com.techinterface.model.request.AddCategoryDto;
import com.techinterface.service.CategoryService;

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

@RestController
@RequestMapping("/api/admin/category")

public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Value("${message.id}")
    private String idIsNotExist;

    @Value("${message.title}")
    private String titleIsExist;

    @GetMapping("")
    public Object getAllCategory() {
        try {
            // trả về danh sách category
            return new ResponseEntity<Object>(categoryService.getAllCategory(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public Object getCategoryById(@PathVariable int id) {
        try {
            // check xem category id có tồn tại hay không
            if (!categoryService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // trả về category theo id gửi lên
            return new ResponseEntity<Object>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public Object post(@RequestBody AddCategoryDto entity) {
        try {
            // check xem category title có bị trùng hay không
            if (categoryService.checkExistByTitle(entity.getTitle()))
                return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

            // thêm category mới vào database
            categoryService.add(entity);
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    public Object put(@RequestBody CategoryDto entity, @PathVariable int id) {
        try {
            // check xem id gửi lên và id trong category có giống nhau hay không
            if (id != entity.getId())
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

            // check xem category id có tồn tại hay không
            if (!categoryService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // check xem category title có bị trùng hay không
            if (categoryService.checkExistByTitle(entity.getTitle()))
                return new ResponseEntity<Object>("Title is exist", HttpStatus.BAD_REQUEST);

            // thay đổi category dưới database
            categoryService.edit(entity);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable int id) {
        try {
            // check xem category id có tồn tại hay không
            if (!categoryService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // xóa category dưới database
            categoryService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

}
