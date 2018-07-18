package com.example.wangyinews.wangyinews.Bean;


public class UserBeanRegister {
    private String username;
    private String iphone;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return iphone;
    }

    public void setPhone(String phone) {
        this.iphone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public UserBeanRegister(String username,String email, String iphone, String password) {
        this.username = username;
        this.iphone = iphone;
        this.email = email;
        this.password = password;
    }

}
