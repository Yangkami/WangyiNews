package com.example.wangyinews.wangyinews;

import android.app.Application;

import com.example.wangyinews.wangyinews.Bean.UserBean;

public class CoreApplication extends Application {
    private com.example.wangyinews.wangyinews.Bean.UserBean userBean;
    private static  CoreApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static CoreApplication  getInstance(){
        return instance;
    }


    public com.example.wangyinews.wangyinews.Bean.UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
