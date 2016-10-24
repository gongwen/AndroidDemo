package gw.com.code.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by GongWen on 16/10/24.
 */

public class ActionUtil {
    public static void goActionDial(Context context, String tel) {
        if (TextUtils.isEmpty(tel)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
