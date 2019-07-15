package com.carl.springmvc.shiro.realm;

import com.carl.springmvc.beans.Permission;
import com.carl.springmvc.beans.Role;
import com.carl.springmvc.beans.User;
import com.carl.springmvc.common.PasswordHelper;
import com.carl.springmvc.constant.LoginConstants;
import com.carl.springmvc.model.TUser;
import com.carl.springmvc.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 自定义realm 进行登录验证以及权限控制
 * @Author carl.he
 * @Date 2019/6/25 10:38
 * @Version 1.0
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("===执行授权===");

        Subject subject = SecurityUtils.getSubject();
        AuthorizationInfo cacheInfo = (AuthorizationInfo) subject.getSession().getAttribute(LoginConstants.AUTHORIZATION_INFO_KEY);
        if(this.isCachingEnabled()&&cacheInfo!=null){
            return cacheInfo;
        }
        /** 通过用户名得到对应的用户信息（角色，权限等） */
        String username = (String) super.getAvailablePrincipal(principals);

        User user = userService.findUserByName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色与权限字符串集合
        Collection<String> rolesCollection = new HashSet<>();
        Collection<String> permissionCollection = new HashSet<>();
        if(user != null){
            // 读取并赋值用户角色与权限
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                rolesCollection.add(role.getRoleName());
                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions){
                    permissionCollection.add(permission.getPermissionUrl());
                }
            }
        }
        info.addRoles(rolesCollection);
        info.addStringPermissions(permissionCollection);
        subject.getSession().setAttribute(LoginConstants.AUTHORIZATION_INFO_KEY,info);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("===执行登录认证===");

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;

        /** 通过用户名查询数据库得到密码 */
        User user = userService.findUserByName(usernamePasswordToken.getUsername());
//        mapperFacade.map(tUser,User.class);
        System.out.println(user);
        if(user == null){
            throw new UnknownAccountException();
        }

        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserPassword());

        return new SimpleAuthenticationInfo(user.getUserName(), passwordHelper.encryptPassword(user),
                credentialsSalt, user.getUserName());
    }
}
