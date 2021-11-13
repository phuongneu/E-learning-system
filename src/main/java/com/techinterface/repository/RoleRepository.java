package com.techinterface.repository;

import com.techinterface.entity.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findByName(String name);

}
