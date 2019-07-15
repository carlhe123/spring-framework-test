package com.carl.util.validator;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 字符串校验
 * @Author carl.he
 * @Date 2019/5/27 15:22
 * @Version 1.0
 **/
public class StringValidator {
    public StringValidator() {
    }

    public static boolean isLetter(String s) {
        boolean ret = true;
        if (StringUtils.isEmpty(s)) {
            ret = false;
        }

        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char aC = var2[var4];
            if (!Character.isLetter(aC)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static boolean isDigit(String s) {
        boolean ret = true;
        if (StringUtils.isEmpty(s)) {
            ret = false;
        }

        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char aC = var2[var4];
            if (!Character.isDigit(aC)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static boolean isIp(String str) {
        boolean isIp = true;
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        if (str.matches(regex)) {
            String[] arr = str.split("\\.");

            for(int i = 0; i < 4; ++i) {
                int temp = Integer.parseInt(arr[i]);
                if (temp < 0 || temp > 255) {
                    isIp = false;
                    break;
                }
            }
        } else {
            isIp = false;
        }

        return isIp;
    }

    public static boolean match(String pat, String str) {
        Pattern pattern = Pattern.compile(pat);
        Matcher match = pattern.matcher(str);
        return match.find();
    }
}
