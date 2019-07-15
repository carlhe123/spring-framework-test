package com.carl.util.validator;

import org.springframework.util.StringUtils;

/**
 * @Description 手机号校验
 * @Author carl.he
 * @Date 2019/5/27 15:22
 * @Version 1.0
 **/
public class PhoneValidator {
    private static final String LANDLINE_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$";
    private static final String TELECOM_MOBILE_PATTERN = "(^1(33|53|77|8[019])\\d{8}$)|(^1700\\d{7}$)";
    private static final String UNICOM_MOBILE_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^1709\\d{7}$)";
    private static final String MOBILE_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";

    public PhoneValidator() {
    }

    public static boolean checkMobilePhone(String str) {
        return checkTelecomMobile(str) || checkUnicomMobile(str) || checkMobileMobile(str);
    }

    public static boolean checkLandline(String str) {
        return !StringUtils.isEmpty(str) && StringValidator.match("^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$", str);
    }

    public static boolean checkTelecomMobile(String str) {
        return !StringUtils.isEmpty(str) && StringValidator.match("(^1(33|53|77|8[019])\\d{8}$)|(^1700\\d{7}$)", str);
    }

    public static boolean checkUnicomMobile(String str) {
        return !StringUtils.isEmpty(str) && StringValidator.match("(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^1709\\d{7}$)", str);
    }

    public static boolean checkMobileMobile(String str) {
        return !StringUtils.isEmpty(str) && StringValidator.match("(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)", str);
    }
}
