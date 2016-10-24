package gw.com.code.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by GongWen on 16/10/24.
 */

public class SDCardUtil {
    /**
     * 判断存储卡是否可用
     * true为可用
     *
     * @return
     */
    public static boolean hasSDCard() {
        File path = Environment.getExternalStorageDirectory();
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                && path != null && path.canWrite() && path.canRead();
    }

    /**
     * 判断存储卡是否可用
     * true为可用
     *
     * @return
     */
    public static boolean hasSDCardWithToast(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            Toast.makeText(context, "存储卡已被拔除，将暂时无法使用语音、图片等功能", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
