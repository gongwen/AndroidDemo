package gw.com.code.util;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

/**
 * Created by GongWen on 16/10/26.
 */

public class FontUtil {
    //get The smallest width of the text
    public static float getFontWidth(String text, Paint mPaint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        Rect mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);
        return mBound.width();
    }

    //get The smallest height of the text
    public static float getFontHeight(String text, Paint mPaint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        Rect mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);
        return mBound.height();
    }

    public static float getDrawTextXPosition(String text, float baseCenterX, Paint mPaint) {
        if (TextUtils.isEmpty(text) || mPaint == null) {
            return baseCenterX;
        }
        return baseCenterX - FontUtil.getFontMeasureWidth(text, mPaint) / 2;
    }

    public static float getDrawTextYPosition(float baseCenterY, Paint mPaint) {
        if (mPaint == null) {
            return baseCenterY;
        }
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        return baseCenterY - fm.descent + (fm.bottom - fm.top) / 2;
    }

    //get the smallest rectangle that encloses all of the characters
    public static Rect getTextBounds(String text, Paint mPaint) {
        if (TextUtils.isEmpty(text)) {
            return new Rect(0, 0, 0, 0);
        }
        Rect mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);
        return mBound;
    }

    //meaSure The width of the text
    public static float getFontMeasureWidth(String text, Paint mPaint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        return mPaint.measureText(text);
    }

    //meaSure The height of the text
    public static float getFontMeasureHeight(String text, Paint mPaint) {
        if (TextUtils.isEmpty(text) || mPaint == null) {
            return 0;
        }
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        return fm.bottom - fm.top;
    }
}
