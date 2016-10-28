package gw.com.code.base.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GongWen on 16/10/28.
 */

public class RecyclerViewHolder<TH> extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
    private View mItemView;
    private TH data;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
        this.mItemView = itemView;
    }

    public View getItemView() {
        return mItemView;
    }

    public <T extends View> T getView(int viewId) {
        View mView = mViews.get(viewId);
        if (mView == null) {
            mView = mItemView.findViewById(viewId);
            mViews.put(viewId, mView);
        }
        return (T) mView;
    }

    public TH getData() {
        return data;
    }

    public void setData(TH data) {
        this.data = data;
    }

    public void setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
    }
}
