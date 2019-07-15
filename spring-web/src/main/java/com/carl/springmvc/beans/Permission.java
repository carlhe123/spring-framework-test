package com.carl.springmvc.beans;

import java.io.Serializable;

/**
 * @Description 权限信息POJO
 * @Author carl.he
 * @Date 2019/7/5 16:03
 * @Version 1.0
 **/
public class Permission implements Serializable {
    private static final long serialVersionUID = -4573410595067962426L;
    private String permissionId;
    private String permissionName;
    private String permissionUrl;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                '}';
    }
}
