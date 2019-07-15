package com.carl.util.validator;

import ch.qos.logback.classic.pattern.DateConverter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description DateTime校验
 * @Author carl.he
 * @Date 2019/5/27 15:24
 * @Version 1.0
 **/
public class DateTimeValidator {
    public DateTimeValidator() {
    }

//    public static boolean isDateTime(String dateTime) {
//        Date dt = DateConverter.toDate(dateTime);
//        return dt != null;
//    }

    public static boolean isDateTime(String dateTime, String format) {
        boolean ret = false;

        try {
            if (!StringUtils.isEmpty(dateTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.parse(dateTime);
                ret = true;
            }
        } catch (ParseException var4) {
        }

        return ret;
    }
}
