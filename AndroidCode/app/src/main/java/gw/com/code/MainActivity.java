package gw.com.code;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gw.com.code.test.TestFragment;
import gw.com.code.view.TabIndicatorView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabIndicator();
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
        ViewPager mViewPager = (ViewPager) findViewById(R.id.mViewPager);
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
