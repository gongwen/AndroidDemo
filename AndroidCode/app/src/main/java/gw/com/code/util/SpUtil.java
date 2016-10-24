package gw.com.code.util;

import android.content.SharedPreferences;

import gw.com.code.MainApplication;

/**
 * Created by GongWen on 16/10/24.
 */

public class SpUtil {
    public static final String SP_NAMES = "config";
    private static SharedPreferences sp = MainApplication.getInstance().getSharedPreferences(SP_NAMES, 0);

    public static void saveString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static void saveInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static void saveLong(String key, Long value) {
        sp.edit().putLong(key, value).commit();
    }

    public static Long getLong(String key, int defValue) {
        return sp.getLong(key, defValue);
    }

    public static void saveBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static void clear(String key) {
        sp.edit().remove(key).commit();
    }
}
