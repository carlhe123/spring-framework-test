package com.carl.springmvc.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 角色信息POJO
 * @Author carl.he
 * @Date 2019/7/5 15:06
 * @Version 1.0
 **/
public class Role implements Serializable {

    private static final long serialVersionUID = 7384862273131847847L;
    private Integer roleId;
    private String roleName;
    private Set<Permission> permissions = new HashSet<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
