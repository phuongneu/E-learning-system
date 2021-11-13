package com.techinterface.model.request;

public class SignUpDto {

    private String email;

    private String fullname;

    private String password;

    private String phone;

    public SignUpDto() {
    }

    public SignUpDto(String email, String fullname, String password, String phone) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
