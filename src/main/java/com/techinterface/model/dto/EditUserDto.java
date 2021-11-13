package com.techinterface.model.dto;

public class EditUserDto {

    private int id;
    private String email;
    private String fullname;
    private String phone;

    private Integer roleId;

    public EditUserDto(int id, String email, String fullname, String phone, Integer roleId) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
