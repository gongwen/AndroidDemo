package gw.com.code.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

/**
 * Created by GongWen on 16/10/26.
 */

public class DrawTextUtil {
    public static void drawText(Canvas canvas, String text, float x, float y, Paint mPaint, Paint.Align align) {
        if (canvas == null || TextUtils.isEmpty(text) || mPaint == null) {
            return;
        }
        if (align == null || align == Paint.Align.LEFT) {
            mPaint.setTextAlign(Paint.Align.LEFT);
        } else if ((align == Paint.Align.CENTER)) {
            mPaint.setTextAlign(Paint.Align.CENTER);
        } else {
            mPaint.setTextAlign(Paint.Align.RIGHT);
        }
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        if (NumberUtil.isNumeric(text)) {
            y += FontUtil.getFontHeight(text, mPaint);
        } else {
            y += (FontUtil.getFontHeight(text, mPaint) - fm.ascent + fm.top);
        }
        canvas.drawText(text, x, y, mPaint);
    }

    public static void drawTextInCenter(Canvas canvas, String text, float x, float y, Paint mPaint) {
        if (canvas == null || TextUtils.isEmpty(text) || mPaint == null) {
            return;
        }
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text
                , x
                , FontUtil.getDrawTextYPosition(y, mPaint), mPaint);
    }
}
