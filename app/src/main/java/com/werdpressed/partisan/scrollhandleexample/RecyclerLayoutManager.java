package com.werdpressed.partisan.scrollhandleexample;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RecyclerLayoutManager extends LinearLayoutManager {

    private AppBarManager mAppBarManager;

    public RecyclerLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        View firstVisibleChild = recyclerView.getChildAt(0);
        int childHeight = firstVisibleChild.getHeight();
        int distanceInPixels = ((findFirstVisibleItemPosition() - position) * childHeight);
        if (distanceInPixels == 0) {
            distanceInPixels = (int) Math.abs(firstVisibleChild.getY());
        }

        if (position < findFirstVisibleItemPosition()) {
            mAppBarManager.expandAppBar();
        } else if (position > findLastVisibleItemPosition()) {
            mAppBarManager.collapseAppBar();
        }

        SmoothScroller smoothScroller = new SmoothScroller(recyclerView.getContext(), Math.abs(distanceInPixels), 1000);
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //Log.e("TAG", "dY is " + dy);
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    public void setAppBarManager(AppBarManager appBarManager) {
        mAppBarManager = appBarManager;
    }

    private class SmoothScroller extends LinearSmoothScroller {
        private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
        private final float distanceInPixels;
        private final float duration;

        public SmoothScroller(Context context, int distanceInPixels, int duration) {
            super(context);
            this.distanceInPixels = distanceInPixels;
            float millisecondsPerPx = calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
            this.duration = distanceInPixels < TARGET_SEEK_SCROLL_DISTANCE_PX ?
                    (int) (Math.abs(distanceInPixels) * millisecondsPerPx) : duration;
        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return RecyclerLayoutManager.this
                    .computeScrollVectorForPosition(targetPosition);
        }

        @Override
        protected int calculateTimeForScrolling(int dx) {
            float proportion = (float) dx / distanceInPixels;
            return (int) (duration * proportion);
        }
    }
}
