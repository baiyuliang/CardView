package com.byl.mycardview.swipecard;

import android.content.Context;
import android.util.TypedValue;


public class CardConfig {
    //屏幕上最多同时显示几个Item
    public static int MAX_SHOW_COUNT;
    public static int TRANS_Y_GAP;
    public static void initConfig(Context context) {
        MAX_SHOW_COUNT = 5;
        TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, context.getResources().getDisplayMetrics());
    }
}
