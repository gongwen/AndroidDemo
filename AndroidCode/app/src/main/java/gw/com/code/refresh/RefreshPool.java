package gw.com.code.refresh;

import android.view.View;

import gw.com.code.util.DeviceUtil;

/**
 * Created by GongWen on 16/11/30.
 */

public class RefreshPool implements View.OnAttachStateChangeListener {
    private final String TAG = getClass().getSimpleName();

    private boolean isRefreshing = false;
    private long period;//轮询周期
    private View mView;
    private IRefreshAbility mRefreshAbility;
    private boolean isViewShown;

    public RefreshPool(View view, IRefreshAbility refreshAbility, long period) {
        if (view == null) {
            throw new IllegalArgumentException("Param View " + view + " must not be null!");
        } else if (refreshAbility == null) {
            throw new IllegalArgumentException("Param IRefreshAbility " + refreshAbility + " must not be null!");
        }
        this.mView = view;
        this.mRefreshAbility = refreshAbility;
        this.period = period;
        this.mView.addOnAttachStateChangeListener(this);
    }

    public void startRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            isViewShown = isShown();
            mView.post(task);
        }
    }

    private void stopRefresh() {
        if (isRefreshing) {
            mView.removeCallbacks(task);
            isRefreshing = false;
        }
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isViewShown) {
                if (isShown() && DeviceUtil.isScreenOn()) {
                    mRefreshAbility.scheduleTask();
                }
                if (isRefreshing) {
                    mView.postDelayed(this, period);
                }
            } else {
                isViewShown = isShown();
                mView.postDelayed(this, 100);
            }
        }
    };

    private boolean isShown() {
        return mView.isShown();
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        stopRefresh();
    }

    @Override
    public void onViewAttachedToWindow(View v) {
    }
}
