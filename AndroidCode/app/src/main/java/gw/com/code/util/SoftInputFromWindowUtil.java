package gw.com.code.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import gw.com.code.MainApplication;

public class SoftInputFromWindowUtil {
    public static void showSoftInputFromWindow(View mView) {
        InputMethodManager inputManager =
                (InputMethodManager) MainApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(mView, 0);
    }

    public static void hideSoftInputFromWindow(Activity mActivity) {
        InputMethodManager inputManager =
                (InputMethodManager) MainApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
    }
}