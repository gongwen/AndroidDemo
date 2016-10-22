package gw.com.code.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import gw.com.code.R;


/**
 * Created by GongWen on 16/10/20.
 * ViewGroup均分为等宽等高View
 */


public class AvgFlowLayout extends ViewGroup {
    private float density;

    private int AVG_COUNT;//均分个数
    private int itemHeight;//itemView高度
    private int itemWidth;//itemVIew宽度

    private int verticalDividerWidth;//竖直分割线宽度
    private int horizontalDividerHeight;//水平分割线高度
    private boolean isShowHorizontalDivider;//是否现实水平分割线
    private boolean isShowVerticalDivider;//是否现实竖直分割线
    private int dividerColor;//分割线颜色

    public AvgFlowLayout(Context context) {
        super(context, null, 0);
    }

    public AvgFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvgFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        density = getResources().getDisplayMetrics().density;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvgFlowLayout);
        AVG_COUNT = a.getInt(R.styleable.AvgFlowLayout_avgCount, 1);
        verticalDividerWidth = a.getDimensionPixelSize(R.styleable.AvgFlowLayout_verticalDividerWidth, 0);
        horizontalDividerHeight = a.getDimensionPixelSize(R.styleable.AvgFlowLayout_horizontalDividerHeight, 0);
        isShowVerticalDivider = a.getBoolean(R.styleable.AvgFlowLayout_isShowVerticalDivider, false);
        isShowHorizontalDivider = a.getBoolean(R.styleable.AvgFlowLayout_isShowHorizontalDivider, false);
        dividerColor = a.getColor(R.styleable.AvgFlowLayout_dividerColor, 0xffffffff);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isShowHorizontalDivider) {
            horizontalDividerHeight = 0;
        }
        if (!isShowVerticalDivider) {
            verticalDividerWidth = 0;
        }
        int width = View.resolveSize(getResources().getDisplayMetrics().widthPixels, widthMeasureSpec);
        itemWidth = (width - getPaddingLeft() - getPaddingRight() - verticalDividerWidth * (AVG_COUNT - 1)) / AVG_COUNT;
        int height = View.resolveSize(0, heightMeasureSpec);
        if (height == 0) {
            height = height + getPaddingTop() + getPaddingBottom();
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(
                        MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED));
                if (i % AVG_COUNT == 0) {
                    height = height + child.getMeasuredHeight() + (i >= AVG_COUNT ? horizontalDividerHeight : 0);
                    itemHeight = child.getMeasuredHeight();
                }
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int childW = child.getMeasuredWidth();
            final int childH = child.getMeasuredHeight();
            int xPos = getPaddingLeft() + (i % AVG_COUNT) * (itemWidth + verticalDividerWidth);
            int yPos = getPaddingTop() + i / AVG_COUNT * (itemHeight + horizontalDividerHeight);
            child.layout(xPos, yPos, xPos + childW, yPos + childH);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isShowHorizontalDivider && !isShowVerticalDivider) {
            return;
        }
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(dividerColor);
        int yNum = getChildCount() / AVG_COUNT;
        Rect rect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            //取余，取整
            int mod = i % AVG_COUNT;
            int round = i / AVG_COUNT;
            //竖直分割线
            if (isShowVerticalDivider && (mod < (AVG_COUNT - 1))) {
                if (i != (getChildCount() - 1)) {
                    rect.left = getLeft() + (mod + 1) * itemWidth + mod * verticalDividerWidth;
                    rect.right = rect.left + verticalDividerWidth;
                    rect.top = getPaddingTop() + round * itemHeight + round * horizontalDividerHeight;
                    rect.bottom = rect.top + itemHeight;
                    canvas.drawRect(rect, mPaint);
                }

            }
            //水平分割线
            if (isShowHorizontalDivider) {
                if (round < (yNum - 1) || (round == (yNum - 1) && mod < (getChildCount() % AVG_COUNT))) {
                    rect.left = getPaddingLeft() + mod * itemWidth + mod * verticalDividerWidth;
                    rect.right = rect.left + itemWidth;
                    rect.top = getPaddingTop() + (round + 1) * itemHeight + round * horizontalDividerHeight;
                    rect.bottom = rect.top + horizontalDividerHeight;
                    canvas.drawRect(rect, mPaint);
                }
            }
            if (isShowHorizontalDivider && isShowVerticalDivider) {
                if ((mod < (AVG_COUNT - 1))
                        && (i != (getChildCount() - 1))
                        && (round < (yNum - 1) || (round == (yNum - 1) && mod < ((getChildCount() % AVG_COUNT) - 1)))) {
                    rect.left = getLeft() + (mod + 1) * itemWidth + mod * verticalDividerWidth;
                    rect.right = rect.left + verticalDividerWidth;
                    rect.top = getPaddingTop() + (round + 1) * itemHeight + round * horizontalDividerHeight;
                    rect.bottom = rect.top + horizontalDividerHeight;
                    canvas.drawRect(rect, mPaint);
                }
            }
        }
    }


    public void setAvgCount(int AVG_COUNT) {
        this.AVG_COUNT = AVG_COUNT;
        requestLayout();
    }

    public void setVerticalDividerWidth(int verticalDividerWidth) {
        this.verticalDividerWidth = verticalDividerWidth;
        requestLayout();
    }

    public void isShowHorizontalDivider(boolean isShowHorizontalDivider, float horizontalDividerHeight) {
        this.isShowHorizontalDivider = isShowHorizontalDivider;
        this.horizontalDividerHeight = (int) (horizontalDividerHeight * density);
        requestLayout();
    }

    public void isShowVerticalDivider(boolean isShowVerticalDivider, float verticalDividerWidth) {
        this.isShowVerticalDivider = isShowVerticalDivider;
        this.verticalDividerWidth = (int) (verticalDividerWidth * density);
        requestLayout();
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        postInvalidate();
    }
}

