package com.carl.springmvc.mapper;

import com.carl.springmvc.model.TRolePermission;
import java.util.List;

public interface TRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbggenerated
     */
    int insert(TRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbggenerated
     */
    List<TRolePermission> selectAll();
}