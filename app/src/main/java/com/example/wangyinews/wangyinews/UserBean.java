package com.example.wangyinews.wangyinews;

public class UserBean {
    private String user_name;
    private String user_level;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public UserBean(String user_name, String user_level) {
        this.user_name = user_name;
        this.user_level = user_level;
    }

}
