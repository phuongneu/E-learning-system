package com.techinterface.admin.controller;

import org.springframework.web.bind.annotation.RestController;

import com.techinterface.model.dto.EditUserDto;
import com.techinterface.model.request.AddUserDto;
import com.techinterface.service.RoleService;
import com.techinterface.service.UserService;

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
@RequestMapping("/api/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Value("${message.id}")
    private String idIsNotExist;

    @Value("${message.email}")
    private String emailIsExist;

    @Value("${message.phone}")
    private String phoneIsExist;

    @Value("${message.role}")
    private String roleIsNotExist;

    @GetMapping("")
    public Object get() {
        try {
            // trả về danh sách user
            return new ResponseEntity<Object>(userService.listAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public Object get(@PathVariable(name = "id") Integer id) {
        try {
            // check xem user id có tồn tại hay không
            if (!userService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // trả về user theo id gửi lên
            return new ResponseEntity<Object>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public Object post(@RequestBody AddUserDto entity) {
        try {
            // check xem email có tồn tại hay không
            if (userService.checkExistByEmail(entity.getEmail()))
                return new ResponseEntity<Object>(emailIsExist, HttpStatus.BAD_REQUEST);

            // check xem sdt có tồn tại hay không
            if (userService.checkExistByPhone(entity.getPhone()))
                return new ResponseEntity<Object>(phoneIsExist, HttpStatus.BAD_REQUEST);

            // check xem role id có tồn tại hay không
            if (!roleService.checkExistById(entity.getRoleId()))
                return new ResponseEntity<Object>(roleIsNotExist, HttpStatus.BAD_REQUEST);

            // thêm user mới vào database
            userService.add(entity);
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    public Object put(@RequestBody EditUserDto entity, @PathVariable int id) {
        try {
            // check xem id gửi lên và id trong user có giống nhau hay không
            if (id != entity.getId())
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

            // check xem user id có tồn tại hay không
            if (!userService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // check xem sdt có tồn tại hay không
            if (userService.checkExistByPhone(entity.getPhone()))
                return new ResponseEntity<Object>(emailIsExist, HttpStatus.BAD_REQUEST);

            // check xem role id có tồn tại hay không
            if (!roleService.checkExistById(entity.getRoleId()))
                return new ResponseEntity<Object>(roleIsNotExist, HttpStatus.BAD_REQUEST);

            userService.edit(entity);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Integer id) {
        try {
            // check xem user id có tồn tại hay không
            if (!userService.checkExistById(id))
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            // xóa user dưới database
            userService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
}
