package gw.com.code.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import gw.com.code.util.FontUtil;

/**
 * Created by GongWen on 16/10/26.
 */

public class MeasureTextTestView extends View {
    private float density;
    private Paint mPaint;
    private TextPaint mTextPaint;

    public MeasureTextTestView(Context context) {
        this(context, null, 0);
    }

    public MeasureTextTestView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public MeasureTextTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    int centerX, centerY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        drawText(canvas);
    }

    private void drawText(Canvas canvas) {

        canvas.drawColor(0xffaaaaaa);
//        String txt = "测试测试测试";
//        String txt = "01234.56789";
        String txt = "abcdefghijklmn";

        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(50 * density);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();

        float y = FontUtil.getFontHeight(txt, mTextPaint) + 30;
        canvas.drawText(txt, 0, y, mTextPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0, y + fm.top, getWidth(), y + fm.top, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, y + fm.ascent, getWidth(), y + fm.ascent, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, y, getWidth(), y, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, y + fm.descent, getWidth(), y + fm.descent, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, y + fm.bottom, getWidth(), y + fm.bottom, mPaint);

        System.out.println("Font-->FontMetrics.top:" + fm.top
                + ",FontMetrics.ascent:" + fm.ascent
                + ",FontMetrics.descent:" + fm.descent
                + ",FontMetrics.bottom:" + fm.bottom
                + ",Font.height:" + FontUtil.getFontHeight(txt, mTextPaint));
        System.out.println((-fm.descent + (fm.bottom - fm.top) / 2) + "");

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        Rect mBound = FontUtil.getTextBounds(txt, mTextPaint);
        mBound.top += y;
        mBound.bottom += y;
        canvas.drawRect(mBound, mPaint);
        mPaint.setColor(Color.BLACK);
        float top = mBound.top;
        float bottom = top + FontUtil.getFontMeasureHeight(txt, mTextPaint);
        canvas.drawRect(0, top, FontUtil.getFontMeasureWidth(txt, mTextPaint), bottom, mPaint);

        Rect rect = new Rect(0, centerY * 3 / 2, getWidth(), getHeight());
        canvas.drawRect(rect, mPaint);
        mTextPaint.setTextSize(65 * density);
        FontUtil.drawTextInCenter(canvas, txt, (rect.left + rect.right) / 2, (rect.top + rect.bottom) / 2, mTextPaint);

        int position = rect.top - 250;
        canvas.drawLine(0, position, getWidth(), position, mPaint);
        FontUtil.drawText(canvas, txt, 0, position, mTextPaint, Paint.Align.LEFT);
    }
}
