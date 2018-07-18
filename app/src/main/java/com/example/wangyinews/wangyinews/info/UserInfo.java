package com.example.wangyinews.wangyinews.info;

public class UserInfo {

    private static UserInfo instance = new UserInfo();

    private com.example.wangyinews.wangyinews.Bean.UserBean userBean;

    private UserInfo(){}

    public static UserInfo getInstance() {
        return instance;
    }

    public com.example.wangyinews.wangyinews.Bean.UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(com.example.wangyinews.wangyinews.Bean.UserBean userBean) {
        this.userBean = userBean;
    }
}
