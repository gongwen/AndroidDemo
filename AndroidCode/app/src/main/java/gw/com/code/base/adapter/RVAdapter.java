package gw.com.code.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gw.com.code.base.viewholder.RecyclerViewHolder;

/**
 * Created by GongWen on 16/10/27.
 * ViewPager + RecyclerView  实现类似GridView翻页功能
 */

public abstract class RVAdapter<TH> extends PagerAdapter {
    private List<RecyclerView> mRecyclerViews;
    private List<TH> dataList;
    private int pageSize;
    private int pageCount;
    private OnItemClickListener mOnItemClickListener;

    public RVAdapter(Context mContext, List<TH> dataList, int pageSize, int spanCount) {
        this.dataList = dataList;
        this.pageSize = pageSize;

        mRecyclerViews = new ArrayList<>();
        if (dataList == null || dataList.size() == 0) {
            pageCount = 0;
        } else {
            pageCount = ((dataList.size() - 1) / pageSize + 1);
        }
        for (int i = 0; i < pageCount; i++) {
            RecyclerView mRecyclerView = new RecyclerView(mContext);
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, spanCount));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(new RecyclerViewListAdapter(i));
            mRecyclerViews.add(mRecyclerView);
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mRecyclerViews.get(position));
        return mRecyclerViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mRecyclerViews.get(position));
    }

    public interface OnItemClickListener<P> {
        void onItemClick(RecyclerViewHolder holder, P data);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    protected abstract int getLayoutId();

    protected abstract void onBindView(RecyclerViewHolder<TH> holder);

    class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private List<TH> mList;

        public RecyclerViewListAdapter(int pagePosition) {
            mList = new ArrayList<>();
            int endPageSize;
            if (pagePosition == (pageCount - 1)) {
                endPageSize = (dataList.size() - 1) % pageSize + 1;
            } else {
                endPageSize = pageSize;
            }
            for (int i = pagePosition * pageSize; i < (pagePosition * pageSize + endPageSize); i++) {
                mList.add(dataList.get(i));
            }
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext())
                    .inflate(getLayoutId(), parent, false);
            return new RecyclerViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder, mList.get(position));
                    }
                }
            });
            holder.setData(mList.get(position));
            onBindView(holder);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
