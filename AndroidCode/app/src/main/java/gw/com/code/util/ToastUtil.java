package gw.com.code.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showToastShort(Context mContext, String toastStr) {
        Toast.makeText(mContext, toastStr, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context mContext, String toastStr) {
        Toast.makeText(mContext, toastStr, Toast.LENGTH_LONG).show();
    }

}