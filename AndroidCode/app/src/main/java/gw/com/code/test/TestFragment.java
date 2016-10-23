package gw.com.code.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gw.com.code.R;

/**
 * Created by GongWen on 16/10/22.
 */

public class TestFragment extends Fragment {
    private String content;

    public static TestFragment newInstance(String content) {

        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = getArguments().getString("content");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.test_fragment, container, false);
        TextView textView = (TextView) mView.findViewById(R.id.textView);
        textView.setText(content);
        return mView;
    }
}
