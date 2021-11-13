package com.techinterface.model.dto;

public class UserDto {

    private int id;
    private String email;
    private String fullname;
    private String phone;

    private String role;

    public UserDto(int id, String email, String fullname, String phone, String role) {
        super();
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
