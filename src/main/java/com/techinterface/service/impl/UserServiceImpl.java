package com.techinterface.service.impl;

import com.techinterface.entity.Course;
import com.techinterface.entity.Role;
import com.techinterface.entity.User;
import com.techinterface.model.dto.EditCourseDto;
import com.techinterface.model.dto.EditUserDto;
import com.techinterface.model.dto.UserCourseDto;
import com.techinterface.model.dto.UserDto;
import com.techinterface.model.request.AddUserDto;
import com.techinterface.model.request.SignUpDto;
import com.techinterface.repository.RoleRepository;
import com.techinterface.repository.UserRepository;
import com.techinterface.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto dto) {

        Role roleUser = new Role(2);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getEmail(), dto.getFullname(), encodedPassword, dto.getPhone(), roleUser);
        userRepository.save(user);

    }

    @Override
    public boolean checkExistByEmail(String email) {
        if (userRepository.findByEmail(email) == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkPassword(String email, String oldPassword) {

        User user = userRepository.findByEmail(email);
        return passwordEncoder.matches(oldPassword, user.getPassword());

    }

    @Override
    public void changePassword(String email, String newPassword) {

        User user = userRepository.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }

    @Override
    public List<UserDto> listAllUser() {

        List<User> users = (List<User>) userRepository.findAll();

        List<UserDto> dto = new ArrayList<>();
        for (User user : users) {
            dto.add(new UserDto(user.getId(), user.getEmail(), user.getFullname(), user.getPhone(),
                    user.getRole().getName()));
        }

        return dto;
    }

    @Override
    public boolean checkExistById(Integer id) {
        return userRepository.findById(id).isPresent();
    }

    @Override
    public EditUserDto getUserById(Integer id) {
        User user = userRepository.findById(id).get();
        EditUserDto dto = new EditUserDto(user.getId(), user.getEmail(), user.getFullname(), user.getPhone(),
                user.getRole().getId());
        return dto;
    }

    @Override
    public boolean checkExistByPhone(String phone) {
        User user = userRepository.findByPhone(phone);

        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public void add(AddUserDto dto) {

        Role role = new Role(dto.getRoleId());
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getEmail(), dto.getFullname(), encodedPassword, dto.getPhone(), role);
        userRepository.save(user);

    }

    @Override
    public void edit(EditUserDto dto) {
        User user = userRepository.findById(dto.getId()).get();
        Role role = new Role(dto.getRoleId());

        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setPhone(dto.getPhone());
        user.setRole(role);

        userRepository.save(user);

    }

    @Override
    public void deleteById(Integer id) {

        userRepository.deleteById(id);

    }

    @Override
    public List<UserCourseDto> getAllCourseByUser(Integer userId) {

        User user = userRepository.findById(userId).get();
        List<Course> courses = (List<Course>) user.getCourses();

        List<UserCourseDto> dto = new ArrayList<>();

        for (Course course : courses) {
            dto.add(new UserCourseDto(course.getId(), course.getTitle()));
        }

        return dto;
    }

}
