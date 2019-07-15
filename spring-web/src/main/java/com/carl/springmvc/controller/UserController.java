package com.carl.springmvc.controller;

import com.carl.springmvc.beans.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description {@link User} Controller
 * @Author carl.he
 * @Date 2019/5/24 10:05
 * @Version 1.0
 **/
@Controller
public class UserController {

    private final ConcurrentMap<Integer,User> map = new ConcurrentHashMap<>();

    /**
     * @Author carl.he
     * @Description 根据URL中的id 查询对应的{@link User}
     * @Date 2019/5/24 10:34
     * @param id	User id
     * @return {@link User} 如没有该ID的User 返回新的User对象
     **/
    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id){
        System.out.println("User ID:"+id);
        if (map.get(id)!=null) {
            return map.get(id);
        }
        return new User();
    }

    /**
     * @Author carl.he
     * @Description 查询全部的 {@link User}
     * @Date 2019/5/24 10:36
     * @param
     * @return {@link Collection< User>} 如没有User 返回空的list
     **/
    @RequestMapping("/user/list")
    @ResponseBody
    public Collection<User> getAllUser(){
        if (map.values()!=null) {
            return map.values();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @Author carl.he
     * @Description 保存 {@link User}用户
     * @Date 2019/5/24 10:40
     * @param user	用户
     * @return {@link Boolean}
     **/
    @RequestMapping(value = "/user/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean saveUser(@RequestBody User user){
        if (user.getUserId()!=null) {
            map.put(user.getUserId(),user);
            System.out.println("save User Success.User:"+user);
            return true;
        }
        return false;
    }
    @GetMapping("/test/treeview")
    public String testTreeView(){
        return "html/treeview";
    }

}
