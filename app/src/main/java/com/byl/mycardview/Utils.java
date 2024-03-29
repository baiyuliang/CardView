package com.byl.mycardview;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

    public static int getScreenWidth(Activity activity) {
        int width = 0;
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        width = display.getWidth();
        return width;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
