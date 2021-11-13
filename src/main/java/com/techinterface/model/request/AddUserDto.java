package com.techinterface.model.request;

public class AddUserDto {

    private String email;
    private String fullname;
    private String phone;

    private String password;

    private Integer roleId;

    public AddUserDto(String email, String fullname, String phone, String password, Integer roleId) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
