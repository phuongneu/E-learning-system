package com.techinterface.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techinterface.entity.Role;
import com.techinterface.model.dto.RoleDto;
import com.techinterface.model.request.AddRoleDto;
import com.techinterface.repository.RoleRepository;
import com.techinterface.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void add(AddRoleDto entity) {

        Role role = new Role(entity.getName(), entity.getDescription());
        roleRepository.save(role);

    }

    @Override
    public List<RoleDto> getAllRole() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        List<RoleDto> dto = new ArrayList<>();

        for (Role role : roles) {
            dto.add(new RoleDto(role.getId(), role.getName(), role.getDescription()));
        }
        return dto;
    }

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.findById(id).get();
        return new RoleDto(role.getId(), role.getName(), role.getDescription());
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);

    }

    @Override
    public void edit(RoleDto dto) {
        Role role = roleRepository.findById(dto.getId()).get();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        roleRepository.save(role);

    }

    @Override
    public boolean checkExistByName(String name) {
        Role role = roleRepository.findByName(name);

        if (role == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkExistById(Integer id) {
        Role role = roleRepository.findById(id).get();

        if (role == null) {
            return false;
        }
        return true;
    }

}
