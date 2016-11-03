package gw.com.code.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import gw.com.code.R;

/**
 * Created by GongWen on 16/11/2.
 * 练习drawBitmap(Bitmap bitmap,Rect src,Rect dst,Paint paint)的用法
 */

public class CheckView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private int width;
    private int height;
    private float progress = 0;
    private float progressKD = 0.02f;
    private boolean isReverse = false;

    public CheckView(Context context) {
        this(context, null, 0);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.check);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, (float) (Math.max(width, height) * 0.8), paint);
        if (!isReverse) {
            Rect src = new Rect(0, 0, (int) (width * progress), height);
            Rect dst = new Rect(-width / 2, -height / 2, -width / 2 + (int) (width * progress), height / 2);
            canvas.drawBitmap(bitmap, src, dst, null);
            progress += progressKD;
            postInvalidate();
        } else {
            //从Bitmap左上角开始裁剪
            Rect src = new Rect(0, 0, (int) (width * progress), height);
            //subBitmap显示区域
            Rect dst = new Rect(-width / 2, -height / 2, -width / 2 + (int) (width * progress), height / 2);
            canvas.drawBitmap(bitmap, src, dst, null);
            progress -= progressKD;
            postInvalidate();
        }
        if (progress > 1) {
            isReverse = true;
        } else if (progress < 0) {
            isReverse = false;
        }
        canvas.restore();
    }
}
