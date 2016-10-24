package gw.com.code.util;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GongWen on 16/10/23.
 */

public class DecodeUtil {
    public static String getStringToBase64(String str) {
        String passContent = (int) Math.round(Math.random() * 899 + 100) + str + (int) Math.round(Math.random() * 899 + 100);
        return Base64.encodeToString(passContent.getBytes(), Base64.NO_WRAP);
    }

    public static String Md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String encodeUTF(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        } else {
            return "";
        }
    }

    public static String decodeUTF(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        } else {
            return "";
        }
    }
}
