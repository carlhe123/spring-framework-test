package com.carl.springmvc.mapper;

import com.carl.springmvc.model.TUserRole;
import java.util.List;

public interface TUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbggenerated
     */
    int insert(TUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbggenerated
     */
    List<TUserRole> selectAll();
}