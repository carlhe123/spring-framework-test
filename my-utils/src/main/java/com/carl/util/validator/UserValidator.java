package com.carl.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 用户名校验
 * @Author carl.he
 * @Date 2019/5/27 15:25
 * @Version 1.0
 **/
public class UserValidator {

    public UserValidator() {
    }

    public static boolean isValid(String userName) {
        if (userName == null) {
            return false;
        } else {
            String reg = "^(?=.*[a-zA-Z])(?=.*[a-zA-Z0-9_])\\w{3,15}$";
            Pattern regex = Pattern.compile(reg);
            Matcher matcher = regex.matcher(userName);
            return matcher.matches();
        }
    }
}
