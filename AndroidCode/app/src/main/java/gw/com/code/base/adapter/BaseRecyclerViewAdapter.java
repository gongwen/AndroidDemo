package gw.com.code.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gw.com.code.base.viewholder.RecyclerViewHolder;

/**
 * Created by GongWen on 16/10/28.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<T> dataList;
    private Context mContext;
    private LayoutInflater inflater;

    public BaseRecyclerViewAdapter(Context mContext, List<T> mList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.dataList = mList;
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = inflater.inflate(getLayoutId(), parent, false);
        return new RecyclerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder);
                }
            }
        });
        holder.setData(dataList.get(position));
        onBindView(holder);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected abstract int getLayoutId();

    protected abstract void onBindView(RecyclerViewHolder<T> holder);

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(RecyclerViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
