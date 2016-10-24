package gw.com.code.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GongWen on 16/10/23.
 */

public class DateUtil {
    /**
     * 按要求格式返回当前时间
     *
     * @param time     时间
     * @param dateType "yy-MM-dd HH:mm:ss"等
     * @return
     */
    public static String getDateStr(long time, String dateType) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateType);
        return formatter.format(new Date(time));
    }

    public static String getNowDateStr(String dateType) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateType);
        return formatter.format(new Date());
    }

    public static Date getDateByStr(String date, String dateType) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateType);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
