package gw.com.code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import gw.com.code.view.TabIndicatorView;

public class MainActivity extends AppCompatActivity {

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
        TabIndicatorView indicator3 = (TabIndicatorView) findViewById(R.id.indicator3);
        indicator1.setTitles(titles);
        indicator2.setTitles(titles);
        indicator3.setTitles(titles);

        TabIndicatorView.OnTabSelectedListener listener=new TabIndicatorView.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Toast.makeText(MainActivity.this, "onTabSelected--->" + titles.get(position), Toast.LENGTH_SHORT).show();
                ;
            }

            @Override
            public void onTabReselected(int position) {
                Toast.makeText(MainActivity.this, "onTabReselected--->" + titles.get(position), Toast.LENGTH_SHORT).show();
                ;
            }
        };
        indicator1.setOnTabSelectedListener(listener);
        indicator2.setOnTabSelectedListener(listener);
        indicator3.setOnTabSelectedListener(listener);
    }
}
