package com.carl.springmvc.service.impl;

import com.carl.springmvc.beans.User;
import com.carl.springmvc.mapper.TUserMapper;
import com.carl.springmvc.model.TUser;
import com.carl.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description 用户服务实现类
 * @Author carl.he
 * @Date 2019/7/8 12:05
 * @Version 1.0
 **/

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public User findUserByName(String userName) {
        List<User> userList =  userMapper.selectByName(userName);
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }
}
