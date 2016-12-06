package gw.com.code.util;

import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import gw.com.code.MainApplication;

/**
 * Created by GongWen on 16/10/24.
 */

public class DeviceUtil {
    private static DisplayMetrics metrics;

    public static int getScreenWidth() {
        if (metrics == null) {
            metrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return metrics.widthPixels;
    }

    public static int getScreeHeight() {
        if (metrics == null) {
            metrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return metrics.heightPixels;
    }

    public static float getScreenDensity() {
        if (metrics == null) {
            metrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return metrics.density;
    }

    public static float px2dp(float value) {
        return px2other(TypedValue.COMPLEX_UNIT_DIP, value);
    }

    public static float dp2px(float value) {
        return other2px(TypedValue.COMPLEX_UNIT_DIP, value);
    }

    public static float sp2px(float value) {
        return other2px(TypedValue.COMPLEX_UNIT_SP, value);
    }

    public static float other2px(int unit, float value) {
        if (metrics == null) {
            metrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        return TypedValue.applyDimension(unit, value, metrics);
    }

    public static float px2other(int unit, float value) {
        if (metrics == null) {
            metrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        }
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value / metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value / metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value / (metrics.xdpi * (1.0f / 72));
            case TypedValue.COMPLEX_UNIT_IN:
                return value / metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value / (metrics.xdpi * (1.0f / 25.4f));
        }
        return 0;
    }

    public static boolean isScreenOn() {
        PowerManager pm = (PowerManager) MainApplication.getInstance().getSystemService(MainApplication.getInstance().POWER_SERVICE);
        if (pm.isScreenOn()) {
            return true;
        }
        return false;
    }
}
