package com.carl.springmvc.model;

/**
 * @Description TODO Carl写点注释吧！
 * @Author carl.he
 * @Date 2019/5/24 10:24
 * @Version 1.0
 **/
public class User {

    private String name;
    private Integer id;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", sex='" + sex + '\'' +
                '}';
    }
}
