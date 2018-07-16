package com.example.wangyinews.wangyinews.Bean;

public class UserBeanLogin {

    private String useraccount;
    private String password;
    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBeanLogin(String useraccount, String password) {
        this.useraccount = useraccount;
        this.password = password;
    }
}