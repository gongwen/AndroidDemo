package gw.com.code.util;

import java.text.DecimalFormat;

/**
 * Created by GongWen on 16/10/24.
 */

public class NumberUtil {

    /*
    * 0.00  #.##  区别
    * 小数部分 #代表最多有几位，0代表必须有且只能有几位
    * @params savePoint:保留小数点的位数
    * */
    public static String formatNumberDot(double d, int savePoint) {
        String pattern = "0.";
        for (int i = 0; i < savePoint; i++) {
            pattern += "0";
        }
        DecimalFormat df = new DecimalFormat(pattern);//格式化小数
        return df.format(d);
    }

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

}
