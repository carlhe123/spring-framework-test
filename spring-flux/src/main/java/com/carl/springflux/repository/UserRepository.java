package com.carl.springflux.repository;

import com.carl.springflux.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description {@link User 用户} 仓储
 * @Author carl.he
 * @Date 2019/5/27 9:01
 * @Version 1.0
 **/
@Repository
public class UserRepository{
    /** 将用户保存在map中 key为用户ID */
    private final ConcurrentMap<Integer, User> map = new ConcurrentHashMap<>();

    /**
     * @Author carl.he
     * @Description 查询全部用户
     * @Date 2019/5/27 9:16
     * @param
     * @return {@link Collection< User>} 如果没有用户返回<code>Collections.EMPTY_LIST</code>
     **/
    public Collection<User> findAll() {

        return map.values();
    }

    /**
     * @Author carl.he
     * @Description 保存用户
     * @Date 2019/5/27 9:19
     * @param user	{@link User 用户}
     * @return {@link Boolean} 如果保存成功，返回<code>true</code>，保存失败,返回<code>false</code>
     **/
    public Boolean saveUser(User user) {
        if (map.containsKey(user.getId())){
            return false;
        }
        return map.put(user.getId(),user) == null;
    }
}
