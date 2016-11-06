package gw.com.code.util;

import android.content.Context;
import android.content.Intent;

import gw.com.code.test.PathActivity;

/**
 * Created by GongWen on 16/11/4.
 */

public class ActivityUtil {
    public static void goPathActivity(Context context) {
        context.startActivity(new Intent(context, PathActivity.class));
    }
}
