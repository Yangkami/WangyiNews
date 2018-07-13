package com.example.wangyinews.wangyinews.info;

import com.example.wangyinews.wangyinews.UserBean;

public class UserInfo {

    private static UserInfo instance = new UserInfo();

    private UserBean userBean;

    private UserInfo(){}

    public static UserInfo getInstance() {
        return instance;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
