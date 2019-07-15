package com.carl.springmvc.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 用户信息POJO
 * @Author carl.he
 * @Date 2019/5/24 10:24
 * @Version 1.0
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 5625754489381937062L;
    private Integer userId;
    private String userName;
    private String userPassword;
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", roles=" + roles +
                '}';
    }
}
