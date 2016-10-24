package gw.com.code.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by GongWen on 16/10/23.
 * 计算View宽高的几种常见方式
 */

public class ViewMeasureUtil {
    /*
    getMeasuredHeight()与getHeight的区别

    实际上在当屏幕可以包裹内容的时候，他们的值相等，
    只有当view超出屏幕后，才能看出他们的区别：
    getMeasuredHeight()是实际View的大小，与屏幕无关，
    而getHeight的大小此时则是屏幕的大小。
    当超出屏幕后，getMeasuredHeight()等于getHeight()加上屏幕之外没有显示的大小
    */
    public static View getMeasuredView(View mView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mView.measure(w, h);
        return mView;
    }

    public static void testMeasuredViewForObserver(final View mView) {
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("MeasuredView -->width", mView.getMeasuredWidth() + "");
                Log.i("MeasuredView -->height", mView.getMeasuredHeight() + "");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public static void testMeasuredViewForPost(final View mView) {
        mView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("MeasuredView -->width", mView.getMeasuredWidth() + "");
                Log.i("MeasuredView -->height", mView.getMeasuredHeight() + "");
            }
        });
    }
}
