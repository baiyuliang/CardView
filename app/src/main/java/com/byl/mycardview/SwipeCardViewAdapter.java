package com.byl.mycardview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class SwipeCardViewAdapter extends RecyclerView.Adapter<SwipeCardViewAdapter.MyViewHolder> {

    Context context;
    List<ItemBean> itemBeanList;

    public SwipeCardViewAdapter(Context context, List<ItemBean> itemBeanList) {
        this.context = context;
        this.itemBeanList = itemBeanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new SwipeCardViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        int W = Utils.getScreenWidth((Activity) context) - Utils.Dp2Px(context, 30);
        int H = W * 631 / 1031;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(W, H);
        viewHolder.iv_card.setLayoutParams(layoutParams);
        ItemBean itemBean=itemBeanList.get(i);
        viewHolder.iv_card.setImageResource(itemBean.res);
    }

    @Override
    public int getItemCount() {
        return itemBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_card = itemView.findViewById(R.id.iv_card);
        }
    }
}
