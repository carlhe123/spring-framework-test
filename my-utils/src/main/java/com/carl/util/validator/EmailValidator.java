package com.carl.util.validator;

/**
 * @Description 邮箱校验
 * @Author carl.he
 * @Date 2019/5/27 15:21
 * @Version 1.0
 **/
public class EmailValidator {
    public EmailValidator() {
    }

    public static boolean check(String email) {
        String check = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return StringValidator.match(check, email);
    }
}
