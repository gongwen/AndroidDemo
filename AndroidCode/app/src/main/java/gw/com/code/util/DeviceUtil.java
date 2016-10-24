package gw.com.code.util;

import android.util.DisplayMetrics;

import gw.com.code.MainApplication;

/**
 * Created by GongWen on 16/10/24.
 */

public class DeviceUtil {
    private static DisplayMetrics displayMetrics;

    public static int getScreenWidth() {
        if (displayMetrics == null) {
            displayMetrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreeHeight() {
        if (displayMetrics == null) {
            displayMetrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return displayMetrics.heightPixels;
    }

    public static float getScreenDensity() {
        if (displayMetrics == null) {
            displayMetrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return displayMetrics.density;
    }

}
