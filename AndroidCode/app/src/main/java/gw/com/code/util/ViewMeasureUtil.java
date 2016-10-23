package gw.com.code.util;

import android.view.View;

/**
 * Created by GongWen on 16/10/23.
 */

public class ViewMeasureUtil {
    public static View getMeasuredView(View mView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mView.measure(w, h);
        return mView;
    }
}
