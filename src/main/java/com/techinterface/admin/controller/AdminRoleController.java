package com.techinterface.admin.controller;

import com.techinterface.model.dto.RoleDto;
import com.techinterface.model.request.AddRoleDto;
import com.techinterface.service.RoleService;

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
@RequestMapping("api/admin/role")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    @Value("${message.id}")
    private String idIsNotExist;

    @Value("${message.name}")
    private String nameIsExist;

    @GetMapping("")
    public Object getAllRole() {
        try {

            return new ResponseEntity<Object>(roleService.getAllRole(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("{id}")
    public Object getRole(@PathVariable(name = "id") Integer id) {
        try {
            if (roleService.checkExistById(id) == false) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<Object>(roleService.getRoleById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("")
    public Object addRole(@RequestBody AddRoleDto dto) {
        try {

            if (roleService.checkExistByName(dto.getName())) {
                return new ResponseEntity<Object>(nameIsExist, HttpStatus.BAD_REQUEST);
            }

            roleService.add(dto);
            return new ResponseEntity<Object>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("{id}")
    public Object editRole(@PathVariable(name = "id") Integer id, RoleDto dto) {
        try {

            if (id != dto.getId()) {
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

            }

            if (roleService.checkExistById(id) == false) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);
            }

            if (roleService.checkExistByName(dto.getName())) {
                return new ResponseEntity<Object>(nameIsExist, HttpStatus.BAD_REQUEST);
            }

            roleService.edit(dto);
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Integer id) {
        try {

            if (roleService.checkExistById(id) == false) {
                return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

            }

            roleService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

}
