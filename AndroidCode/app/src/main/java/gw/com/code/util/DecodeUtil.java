package gw.com.code.util;

import android.util.Base64;

/**
 * Created by GongWen on 16/10/23.
 */

public class DecodeUtil {
    public static String getStringToBase64(String str) {
        String passContent = (int) Math.round(Math.random() * 899 + 100) + str + (int) Math.round(Math.random() * 899 + 100);
        return Base64.encodeToString(passContent.getBytes(), Base64.NO_WRAP);
    }
}
