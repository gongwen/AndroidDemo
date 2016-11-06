package gw.com.code;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gw.com.code.base.adapter.RVAdapter;
import gw.com.code.base.viewholder.RecyclerViewHolder;
import gw.com.code.test.TestFragment;
import gw.com.code.test.TitleItemEntity;
import gw.com.code.util.ActivityUtil;
import gw.com.code.util.ToastUtil;
import gw.com.code.view.DotViewPager;
import gw.com.code.view.FolderTextView;
import gw.com.code.view.TabIndicatorView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.pathTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.goPathActivity(MainActivity.this);
            }
        });
        setDotViewPager();
        setFolderTextView();
        setTabIndicator();
    }

    private void setFolderTextView() {
        FolderTextView folderTextView = (FolderTextView) findViewById(R.id.folderTextView);
        folderTextView.setFolderLine(5);
    }

    private void setDotViewPager() {
        DotViewPager mDotViewPager = (DotViewPager) findViewById(R.id.mDotViewPager);
        List<Integer> resList = Arrays.asList(new Integer[]{R.mipmap.king, R.mipmap.king, R.mipmap.king, R.mipmap.king, R.mipmap.king, R.mipmap.king, R.mipmap.king, R.mipmap.king});
        List<String> list = Arrays.asList(new String[]{"王牌产品", "实时解盘", "研究院", "低佣开户", "大参考", "视听讲堂", "财经日历", "智能选股"});

        List<TitleItemEntity> titles = new ArrayList<>();
        for (int i = 0; i < resList.size() * 2; i++) {
            titles.add(new TitleItemEntity(resList.get(i % resList.size()), list.get(i % list.size())));
        }
        mDotViewPager.setData(titles, 8, 4);
        mDotViewPager.getAdapter().setOnItemClickListener(new RVAdapter.OnItemClickListener<TitleItemEntity>() {
            @Override
            public void onItemClick(RecyclerViewHolder holder, TitleItemEntity data) {
                ToastUtil.showToastShort(MainActivity.this, holder.getLayoutPosition() + "-->" + data.getTitle());
            }
        });
    }

    private void setTabIndicator() {
        final List<String> titles = Arrays.asList(new String[]{"简况", "财务", "股东", "综评"});
        TabIndicatorView indicator1 = (TabIndicatorView) findViewById(R.id.indicator1);
        TabIndicatorView indicator2 = (TabIndicatorView) findViewById(R.id.indicator2);
        final TabIndicatorView indicator3 = (TabIndicatorView) findViewById(R.id.indicator3);
        indicator1.setTitles(titles);
        indicator2.setTitles(titles);
        indicator3.setTitles(titles);

        TabIndicatorView.OnTabSelectedListener listener = new TabIndicatorView.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.w(TAG, "onTabSelected--->" + titles.get(position));
                Toast.makeText(MainActivity.this, "onTabSelected--->" + titles.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int position) {
                Log.w(TAG, "onTabReselected--->" + titles.get(position));
                Toast.makeText(MainActivity.this, "onTabReselected--->" + titles.get(position), Toast.LENGTH_SHORT).show();
            }
        };
        indicator1.setOnTabSelectedListener(listener);
        indicator2.setOnTabSelectedListener(listener);
        indicator3.setOnTabSelectedListener(listener);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        final List<TestFragment> fragmentList = new ArrayList<>();
        fragmentList.add(TestFragment.newInstance("test one"));
        fragmentList.add(TestFragment.newInstance("test two"));
        fragmentList.add(TestFragment.newInstance("test three"));
        fragmentList.add(TestFragment.newInstance("test four"));
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Arrays.asList(new String[]{"简况", "财务", "股东", "综评"}).get(position);
            }
        };
        mViewPager.setAdapter(adapter);
        indicator3.setupWithViewPager(mViewPager);
    }

}
