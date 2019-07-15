package com.carl.springmvc.controller;

import com.carl.springmvc.constant.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description 登录Controller
 * @Author carl.he
 * @Date 2019/6/25 11:54
 * @Version 1.0
 **/
@Controller
public class LoginController {

    /**
     * @param
     * @return {@link String}
     * @Author carl.he
     * @Description 登出
     * @Date 2019/6/25 14:56
     **/
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "redirect:/login.html";
    }

    /**
     * 登陆
     *
     * @param map 存储用户名和密码的map
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam Map<String, String> map) {
        // 从SecurityUtils里边创建一个 subject
        Subject user = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("username"), map.get("password"));
        // 执行认证登陆
        try {
            user.login(token);
            user.getSession().setTimeout(LoginConstants.SESSION_VALID_TIME_IN_SEC);
            //根据权限，指定返回数据
            return new ModelAndView("redirect:/main", "errorMsg", "");
        } catch (UnknownAccountException e) {
            token.clear();
            return new ModelAndView("login", "loginModel", "用户名不存在");
        } catch (LockedAccountException e) {
            token.clear();
            return new ModelAndView("login", "loginModel", "该用户已被冻结");
        } catch (AuthenticationException e) {
            token.clear();
            return new ModelAndView("login", "loginModel", "密码不正确");
        }
    }

    @RequestMapping("/main")
    public String index(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("root", request.getContextPath());
        return "index";
    }
}
