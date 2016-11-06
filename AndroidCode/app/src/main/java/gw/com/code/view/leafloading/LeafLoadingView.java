package gw.com.code.view.leafloading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import gw.com.code.R;
import gw.com.code.util.FontUtil;

/**
 * Created by GongWen on 16/11/6.
 * todo leaf飘落动画
 */

public class LeafLoadingView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Paint mPaint, mBitmapPaint;
    private TextPaint mTextPaint;
    private Bitmap fanBitmap, leafBitmap;
    private int fanWidth, fanHeight;
    private int canvasWidth, canvasHeight;
    //边框背景色
    private static final int secondColor = 0xffdddddd;
    //进度条背景色
    private static final int majorColor = 0xffffa800;

    private RectF outerRect, innerRect;
    private float radius = 50, innerPadding = 15;
    private float textSize = radius / 2;
    private float progressWidth;
    private float mFraction = 0;
    private AnimatorSet mAnimatorSet;
    private ValueAnimator loadingAnimator;
    private ValueAnimator fanScaleAnimator;
    private ValueAnimator txtScaleAnimator;
    private long loadingDuration = 2000;
    private long scaleDuration = 1000;
    private Status currentStatus = Status.LOADING;

    public LeafLoadingView(Context context) {
        this(context, null, 0);
    }

    public LeafLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initPaint();
        initBitmap();
        initAnimatorSet();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
        outerRect = new RectF(getPaddingLeft(), canvasHeight / 2 - radius, canvasWidth - getPaddingRight(), canvasHeight / 2 + radius);
        innerRect = new RectF(outerRect.left + innerPadding, outerRect.top + innerPadding, outerRect.right - innerPadding, outerRect.bottom - innerPadding);
        progressWidth = innerRect.right - innerRect.left - (radius - innerPadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xffeeeeee);
        switch (currentStatus) {
            case LOADING:
                drawProgress(canvas, mFraction);
                drawCircle(canvas);
                drawRotateFan(canvas);
                break;
            case FAN_SCALE:
                drawProgress(canvas, 1);
                drawCircle(canvas);
                drawScaleFan(canvas);
                break;
            case TXT_SCALE:
                drawProgress(canvas, 1);
                drawCircle(canvas);
                drawScaleText(canvas);
                break;
        }

    }

    private void drawProgress(Canvas canvas, float mProgressPercent) {
        float right = innerRect.left + progressWidth * mProgressPercent;
        mPaint.setColor(secondColor);
        //边框
        canvas.drawRoundRect(outerRect, radius, radius, mPaint);
        mPaint.setColor(majorColor);
        canvas.save();
        Path mRoundPath = new Path();
        mRoundPath.addRoundRect(innerRect, radius - innerPadding, radius - innerPadding, Path.Direction.CCW);
        canvas.clipPath(mRoundPath, Region.Op.REPLACE);
        canvas.drawRect(new RectF(innerRect.left, innerRect.top, right, innerRect.bottom), mPaint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        //画风扇大背景
        canvas.drawCircle(outerRect.right - radius, outerRect.top + radius, radius, mPaint);
        mPaint.setColor(majorColor);
        //画风扇小背景
        canvas.drawCircle(outerRect.right - radius, outerRect.top + radius, radius - 10, mPaint);
    }

    private void drawRotateFan(Canvas canvas) {
        //画旋转风扇
        canvas.save();
        canvas.translate(outerRect.right - radius, outerRect.top + radius);
        Matrix matrix = new Matrix();
        matrix.postRotate(360 * 2 * mFraction, fanWidth / 2, fanHeight / 2);
        matrix.postTranslate(-fanWidth / 2, -fanHeight / 2);
        canvas.drawBitmap(fanBitmap, matrix, mBitmapPaint);
        canvas.restore();
    }

    private void drawScaleFan(Canvas canvas) {
        float scale = 1 - mFraction;
        //画缩放风扇
        canvas.save();
        canvas.translate(outerRect.right - radius, outerRect.top + radius);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        matrix.postTranslate(-fanWidth / 2 * scale, -fanHeight / 2 * scale);
        canvas.drawBitmap(fanBitmap, matrix, mBitmapPaint);
        canvas.restore();

    }

    private void drawScaleText(Canvas canvas) {
        String finishedText = "100%";
        mTextPaint.setTextSize(textSize * mFraction);
        canvas.save();
        canvas.translate(outerRect.right - radius, outerRect.top + radius);
        canvas.drawText(finishedText, 0, FontUtil.getFontHeight(finishedText, mTextPaint) / 2, mTextPaint);
        canvas.restore();
    }

    private void initBitmap() {
        fanBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fan);
        leafBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.leaf);
        fanWidth = fanBitmap.getWidth();
        fanHeight = fanBitmap.getHeight();
    }

    private void initPaint() {
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mBitmapPaint.setFilterBitmap(true);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initAnimatorSet() {
        mAnimatorSet = new AnimatorSet();
        loadingAnimator = ValueAnimator.ofFloat(0, 1);
        fanScaleAnimator = ValueAnimator.ofFloat(1, 0);
        txtScaleAnimator = ValueAnimator.ofFloat(0, 1);

        loadingAnimator.setDuration(loadingDuration);
        fanScaleAnimator.setDuration(scaleDuration);
        txtScaleAnimator.setDuration(scaleDuration);

        loadingAnimator.addUpdateListener(this);
        fanScaleAnimator.addUpdateListener(this);
        txtScaleAnimator.addUpdateListener(this);

        loadingAnimator.addListener(animationListener);
        fanScaleAnimator.addListener(animationListener);
        txtScaleAnimator.addListener(animationListener);

        mAnimatorSet.playSequentially(loadingAnimator, fanScaleAnimator, txtScaleAnimator);
        mAnimatorSet.start();
    }

    public enum Status {
        LOADING,
        FAN_SCALE,
        TXT_SCALE
    }

    private AnimatorListenerAdapter animationListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            switch (currentStatus) {
                case LOADING:
                    currentStatus = Status.FAN_SCALE;
                    break;
                case FAN_SCALE:
                    currentStatus = Status.TXT_SCALE;
                    break;
                case TXT_SCALE:
                    break;
            }
        }
    };

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mFraction = animation.getAnimatedFraction();
        postInvalidate();
    }
}
