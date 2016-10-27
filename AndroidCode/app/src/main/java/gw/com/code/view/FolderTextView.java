package gw.com.code.view;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import gw.com.code.R;

/**
 * Created by evan on 2016/3/3.
 */
public class FolderTextView extends TextView implements View.OnClickListener {

    private int folderLine = Integer.MAX_VALUE;
    private int folderRes, unFolderRes;

    public FolderTextView(Context context) {
        this(context, null, 0);
    }

    public FolderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FolderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
        //解决View too large to fit into drawing cache
        //关闭硬件加速
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        folderRes = R.mipmap.folder;
        unFolderRes = R.mipmap.unfolder;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getLineCount() > folderLine) {
                    isNeedFolder = true;
                    setMaxLines(folderLine);
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, unFolderRes);
                }
            }
        }, 200);
    }

    private boolean isFolder = true;
    private boolean isNeedFolder = false;

    @Override
    public void onClick(View v) {
        if (isNeedFolder) {
            setMaxLines(isFolder ? Integer.MAX_VALUE : folderLine);
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, isFolder ? folderRes : unFolderRes);
            /*if (!isFolder) {
                ViewGroup mViewGroup = (ViewGroup) getParent();
                while (true) {
                    if (mViewGroup == null || mViewGroup instanceof ContentFrameLayout) {
                        break;
                    }
                    if (mViewGroup instanceof ScrollView) {
                        mViewGroup.scrollTo(0, getTop());
                        break;
                    }
                    mViewGroup = (ViewGroup) mViewGroup.getParent();
                }
            }*/
            isFolder = !isFolder;
        }
    }

    // TODO: 16/10/27 优化 
    public void setFolderLine(int folderLine) {
        this.folderLine = folderLine;
        setText(getText());
    }

    public void setFolderIcon(int folderRes, int unFolderRes) {
        this.folderRes = folderRes;
        this.unFolderRes = unFolderRes;
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, isFolder ? folderRes : unFolderRes);
    }


}