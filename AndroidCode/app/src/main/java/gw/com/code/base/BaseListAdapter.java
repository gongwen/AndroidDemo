package gw.com.code.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gw.com.code.common.ViewHolder;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> dataList;
    protected LayoutInflater mInflater;
    private int layoutId;

    public BaseListAdapter(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.dataList = new ArrayList<>();
        this.layoutId = layoutId;
    }

    public BaseListAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        getView(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void getView(ViewHolder holder, T t);

    public void setData(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

}
