package com.techinterface.controller;

import com.techinterface.entity.User;
import com.techinterface.model.dto.PasswordDto;
import com.techinterface.model.request.SignUpDto;
import com.techinterface.repository.UserRepository;
import com.techinterface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Value("${message.email}")
    private String emailIsExist;

    @Value("${message.username}")
    private String emailIsNotExist;

    @Value("${message.password}")
    private String passwrodIsNotCorrect;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("changePassword")
    public Object changePassword(@RequestBody PasswordDto dto) {
        try {

            if (userService.checkExistByEmail(dto.getEmail()) == false) {
                return new ResponseEntity<Object>(emailIsNotExist, HttpStatus.BAD_REQUEST);
            }

            if (userService.checkPassword(dto.getEmail(), dto.getOldPassword()) == false) {
                return new ResponseEntity<Object>(passwrodIsNotCorrect, HttpStatus.BAD_REQUEST);
            }

            userService.changePassword(dto.getEmail(), dto.getNewPassword());
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/course")
    public Object listCourse(HttpServletRequest request) {
        try {

            Principal principal = request.getUserPrincipal();
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            if (user != null) {
                return new ResponseEntity<Object>(userService.getAllCourseByUser(user.getId()), HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

}
