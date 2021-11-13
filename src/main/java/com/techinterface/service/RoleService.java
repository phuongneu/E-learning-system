package com.techinterface.service;

import java.util.List;

import com.techinterface.model.dto.RoleDto;
import com.techinterface.model.request.AddRoleDto;

public interface RoleService {

    void add(AddRoleDto entity);

    List<RoleDto> getAllRole();

    RoleDto getRoleById(Integer id);

    void deleteById(Integer id);

    void edit(RoleDto dto);

    boolean checkExistByName(String name);

    boolean checkExistById(Integer id);
}
