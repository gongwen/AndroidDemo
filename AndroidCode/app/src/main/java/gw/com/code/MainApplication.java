package gw.com.code;

import android.app.Application;

/**
 * Created by GongWen on 16/10/23.
 */

public class MainApplication extends Application {
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
