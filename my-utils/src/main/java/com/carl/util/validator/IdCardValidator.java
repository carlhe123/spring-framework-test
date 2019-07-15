package com.carl.util.validator;

/**
 * @Description IDCard校验
 * @Author carl.he
 * @Date 2019/5/27 15:23
 * @Version 1.0
 **/
public class IdCardValidator {
    public IdCardValidator() {
    }

    public static boolean check(String idCode) {
        if (idCode != null && idCode.length() == 18 && idCode.matches("\\d{17}[0-9X]")) {
            int[] factor = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            char[] random = "10X98765432".toCharArray();
            int total = 0;

            for(int i = 0; i < 17; ++i) {
                total += Character.getNumericValue(idCode.charAt(i)) * factor[i];
            }

            return random[total % 11] == idCode.charAt(17);
        } else {
            return false;
        }
    }
}
