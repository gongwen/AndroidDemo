package gw.com.code.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

import gw.com.code.MainApplication;

/**
 * Created by GongWen on 16/10/23.
 */

public class AppUtil {
    private static String versionName;
    private static int versionCode;

    public static String getVersionName() {
        return versionName == null ? versionName = getPackageInfo().versionName : versionName;
    }

    private static PackageInfo getPackageInfo() {
        try {
            MainApplication context = MainApplication.getInstance();
            return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static int getVersionCode() {
        return versionCode == -1 ? versionCode = getPackageInfo().versionCode : versionCode;
    }

    public static boolean isAppOnForeground(Context context) {

        final String packageName = context.getPackageName();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = activityManager.getRunningTasks(1);

        if (list != null && list.size() > 0) {
            ComponentName cn = list.get(0).topActivity;
            if (packageName.equals(cn.getPackageName())) {
                return true;
            } else {
                return false;
            }

        }

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
