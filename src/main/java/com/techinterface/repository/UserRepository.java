package com.techinterface.repository;

import java.util.Optional;

import com.techinterface.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

    public User findByPhone(String phone);
}
