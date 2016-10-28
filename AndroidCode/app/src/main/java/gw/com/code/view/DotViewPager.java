package gw.com.code.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import gw.com.code.R;
import gw.com.code.base.adapter.RVAdapter;
import gw.com.code.base.viewholder.RecyclerViewHolder;
import gw.com.code.test.TitleItemEntity;

public class DotViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {
    private float density;
    private ViewPager mViewPager;
    private LinearLayout llDot;
    private RVAdapter<TitleItemEntity> adapter;

    public DotViewPager(Context context) {
        this(context, null, 0);
    }

    public DotViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        View mView = LayoutInflater.from(context).inflate(R.layout.dot_view_pager, this, true);
        mViewPager = (ViewPager) mView.findViewById(R.id.dotViewPager);
        mViewPager.addOnPageChangeListener(this);
        llDot = (LinearLayout) mView.findViewById(R.id.llDot);
    }

    public void setData(List<TitleItemEntity> mList, int pageSize, int spanCount) {
        if (mList == null || mList.size() == 0) {
            return;
        }
        adapter = new RVAdapter<TitleItemEntity>(getContext(),
                mList, pageSize, spanCount) {
            @Override
            protected int getLayoutId() {
                return R.layout.dot_view_pager_item;
            }

            @Override
            protected void onBindView(RecyclerViewHolder<TitleItemEntity> holder) {
                TextView tv = holder.getView(R.id.tv);
                tv.setText(holder.getData().getTitle());
                tv.setCompoundDrawablesWithIntrinsicBounds(0, holder.getData().getResId(), 0, 0);
            }


        };
        mViewPager.setAdapter(adapter);

        llDot.removeAllViews();
        int pageCount = ((mList.size() - 1) / pageSize + 1);
        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) llDot.getLayoutParams();
        if (pageCount > 1) {
            lp1.setMargins(0, 0, 0, 0);
            for (int i = 0; i < pageCount; i++) {
                ImageView ivDot = new ImageView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) (2.5 * density), (int) (10 * density), (int) (2.5 * density), (int) (10 * density));
                ivDot.setImageResource(R.mipmap.dot_blur);
                llDot.addView(ivDot, params);
            }
            ((ImageView) llDot.getChildAt(0)).setImageResource(R.mipmap.dot_focus);
        } else {
            lp1.setMargins(0, (int) (15 * density), 0, 0);

        }
    }

    public RVAdapter<TitleItemEntity> getAdapter() {
        return adapter;
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < llDot.getChildCount(); i++) {
            ImageView iv = ((ImageView) llDot.getChildAt(i));
            if (i == position) {
                iv.setImageResource(R.mipmap.dot_focus);
            } else {
                iv.setImageResource(R.mipmap.dot_blur);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
