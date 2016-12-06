package gw.com.code.refresh;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import gw.com.code.util.DeviceUtil;

/**
 * Created by GongWen on 16/11/26.
 */

public abstract class RefreshView extends View {
    private Handler mHandler = new Handler();
    private boolean isRefreshing;
    private boolean isShown;
    private long delayMillis = 2 * 1000;

    public RefreshView(Context context) {
        this(context, null, 0);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            isShown = isShown();
            mHandler.post(task);
        }
    }

    private void stopRefresh() {
        if (isRefreshing) {
            mHandler.removeCallbacks(task);
            isRefreshing = false;
        }
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isShown) {
                if (isShown() && DeviceUtil.isScreenOn()) {
                    scheduleTask();
                }
                if (isRefreshing) {
                    mHandler.postDelayed(this, delayMillis);
                }
            } else {
                isShown = isShown();
                mHandler.postDelayed(this, 100);
            }
        }
    };

    public abstract void scheduleTask();

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRefresh();
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

}
