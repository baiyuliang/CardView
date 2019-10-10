package com.byl.mycardview.swipecard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {

    int cardH = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setCardH(int cardH) {
        this.cardH = cardH;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount < 1) {
            return;
        }
        int bottomPosition;
        //边界处理
        if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            bottomPosition = 0;
        } else {
            bottomPosition = itemCount - CardConfig.MAX_SHOW_COUNT;
        }
        //从可见的最底层View开始layout，依次层叠上去
        for (int position = bottomPosition; position < itemCount; position++) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int viewW = getDecoratedMeasuredWidth(view);
            int viewH = getDecoratedMeasuredHeight(view);

            int widthSpace = getWidth() - viewW;
            int heightSpace = getHeight() - viewH;

            //我们在布局时，将childView居中处理，这里也可以改为只水平居中
            layoutDecoratedWithMargins(view, widthSpace / 2, cardH, widthSpace / 2 + viewW, cardH + viewH);
            int level = itemCount - position - 1;
            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * level);
                } else {
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * (level - 1));
                }
                switch (itemCount) {
                    case 5:
                        if (level == 1) {
                            view.setRotation(-2);
                        } else if (level == 2) {
                            view.setRotation(-4);
                        } else if (level == 3) {
                            view.setRotation(-6);
                        } else {
                            view.setRotation(-8);
                        }
                        break;
                    case 4:
                        if (level == 1) {
                            view.setRotation(-2);
                        } else if (level == 2) {
                            view.setRotation(-4);
                        } else {
                            view.setRotation(-8);
                        }
                        break;
                    case 3:
                        if (level == 1) {
                            view.setRotation(-2);
                        } else {
                            view.setRotation(-8);
                        }
                        break;
                    case 2:
                        view.setRotation(-8);
                        break;
                }

                view.setPivotX(viewW);
                view.setPivotY(viewH);
            } else {
                if (itemCount != 1)
                    view.setRotation(0);
            }
        }
    }

}
