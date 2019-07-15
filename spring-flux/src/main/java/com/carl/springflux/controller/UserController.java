package com.carl.springflux.controller;

import com.carl.springflux.model.User;
import com.carl.springflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Description {@link User 用户} 控制类
 * @Author carl.he
 * @Date 2019/5/27 9:00
 * @Version 1.0
 **/

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/list")
    public Collection<User> findAll(){
        return userRepository.findAll();

    }

    @PostMapping("/user/save")
    public Boolean saveUser(@RequestBody User user){
        return userRepository.saveUser(user);
    }
}
