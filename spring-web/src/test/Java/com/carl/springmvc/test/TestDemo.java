package com.carl.springmvc.test;

import com.carl.util.code.StringToUnicodeUtils;
import sun.nio.cs.UnicodeEncoder;

import java.io.UnsupportedEncodingException;

/**
 * @Description 测试类
 * @Author carl.he
 * @Date 2019/7/11 10:56
 * @Version 1.0
 **/
public class TestDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(StringToUnicodeUtils.stringToUnicode("中文"));
        System.out.println(StringToUnicodeUtils.unicodeToString("\\u4e2d\\u6587"));
    }
}
