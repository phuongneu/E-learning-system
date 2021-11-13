package com.techinterface.service;

import com.techinterface.entity.User;
import com.techinterface.model.dto.CourseDto;
import com.techinterface.model.dto.EditUserDto;
import com.techinterface.model.dto.UserCourseDto;
import com.techinterface.model.dto.UserDto;
import com.techinterface.model.request.AddUserDto;
import com.techinterface.model.request.SignUpDto;

import java.util.List;

public interface UserService {

    void signUp(SignUpDto dto);

    List<UserDto> listAllUser();

    EditUserDto getUserById(Integer id);

    boolean checkExistByEmail(String email);

    boolean checkExistByPhone(String phone);

    boolean checkExistById(Integer id);

    boolean checkPassword(String email, String oldPassword);

    void changePassword(String email, String newPassword);

    void add(AddUserDto dto);

    void edit(EditUserDto dto);

    void deleteById(Integer id);

    List<UserCourseDto> getAllCourseByUser(Integer userId);

}
