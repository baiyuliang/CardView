package com.byl.mycardview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.byl.mycardview.swipecard.CardConfig;
import com.byl.mycardview.swipecard.SwipeCallback;
import com.byl.mycardview.swipecard.SwipeCardLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeCardViewAdapter swipeCardViewAdapter;
    List<ItemBean> list;
    ItemTouchHelper itemTouchHelper;
    SwipeCallback swipeCallback;

    ImageView iv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.immersive(this);
        setContentView(R.layout.activity_main);
        CardConfig.initConfig(this);

        recyclerView = findViewById(R.id.mRecyclerView);
        iv_1 = findViewById(R.id.iv_1);

        int width = Utils.getScreenWidth(this);

        int iv1H = width * 816 / 1080;
        iv_1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, iv1H));

        list = new ArrayList<>();
        list.add(new ItemBean(R.mipmap.ic_item5));
        list.add(new ItemBean(R.mipmap.ic_item4));
        list.add(new ItemBean(R.mipmap.ic_item3));
        list.add(new ItemBean(R.mipmap.ic_item2));
        list.add(new ItemBean(R.mipmap.ic_item1));

        swipeCardViewAdapter = new SwipeCardViewAdapter(this, list);
        SwipeCardLayoutManager swipeCardLayoutManager = new SwipeCardLayoutManager();
        swipeCardLayoutManager.setCardH(iv1H);
        recyclerView.setLayoutManager(swipeCardLayoutManager);
        recyclerView.setAdapter(swipeCardViewAdapter);
        swipeCallback = new SwipeCallback(recyclerView, swipeCardViewAdapter, list);
        swipeCallback.setSwipeListener(new SwipeCallback.SwipeListener() {//滑动监听
            @Override
            public void onSwipe(int direction) {
                Toast.makeText(MainActivity.this, "direction>>" + direction, Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


}
