package gw.com.code.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import gw.com.code.R;

/**
 * Created by GongWen on 16/11/4.
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0309/2573.html
 * http://ghui.me/post/2015/10/android-graphics-path/
 * canvas.clipPath(@NonNull Path path, @NonNull Region.Op op)
 * Path.op(Path path, Op op)
 */

public class PathView extends View {
    private Paint mPaint;
    private Path mPath;

    public PathView(Context context) {
        this(context, null, 0);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(true);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        //setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xffeeeeee);
        drawLine(canvas);
        drawQuad(canvas);
        drawCubic(canvas);
        drawArc(canvas);
        drawRoundRect(canvas);
        drawRoundBitmap(canvas);
    }

    /*
    * 画圆角图片－有锯齿
    * */
    private void drawRoundBitmap(Canvas canvas) {
        canvas.save();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        mPath.reset();
        mPath.addCircle(bitmap.getWidth() / 2, 920 + bitmap.getHeight() / 2, bitmap.getHeight() / 2, Path.Direction.CCW);
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, 0, 920, mPaint);
        canvas.restore();

        canvas.save();
        mPath.reset();
        RectF rectF = new RectF(300, 920, 300 + bitmap.getWidth(), 920 + bitmap.getHeight());
        mPath.addRoundRect(rectF, new float[]{0, 0, 0, 0, 35, 35, 35, 35}, Path.Direction.CCW);
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, mPaint);
        canvas.restore();
    }

    /*
    * 画圆角矩形
    * */
    private void drawRoundRect(Canvas canvas) {
        mPath.reset();
        RectF rectF = new RectF(200, 680, 500, 900);
        mPath.addRoundRect(rectF, new float[]{0, 0, 0, 0, 15, 15, 15, 15}, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
    }

    /*
    * 画弧
    * */
    private void drawArc(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(500, 500);
        RectF rectF = new RectF(100, 400, 600, 600);
        mPath.arcTo(rectF, 0, 270, true);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.BLACK);
    }

    /*
    * 画三阶级贝塞尔曲线
    * */
    private void drawCubic(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(100, 300);
        mPath.cubicTo(200, 400, 300, 200, 400, 300);
        canvas.drawPath(mPath, mPaint);
    }

    /*
    * 画二阶级贝塞尔曲线
    * */
    private void drawQuad(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(100, 100);
        mPath.quadTo(200, 200, 300, 100);
        canvas.drawPath(mPath, mPaint);
    }

    /*
     * 画线段
     * */
    private void drawLine(Canvas canvas) {
        mPath.reset();
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10f, 5f}, 0f);
        mPaint.setPathEffect(dashPathEffect);
        mPath.moveTo(50, 50);
        mPath.lineTo(300, 50);
        canvas.drawPath(mPath, mPaint);
        mPaint.setPathEffect(null);
    }
}
