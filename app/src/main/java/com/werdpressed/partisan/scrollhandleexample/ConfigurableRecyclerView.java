package com.werdpressed.partisan.scrollhandleexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ConfigurableRecyclerView extends RecyclerView {

    private boolean isScrollingActive;

    private int y;

    public ConfigurableRecyclerView(Context context) {
        super(context);
    }

    public ConfigurableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return isScrollingActive || super.onTouchEvent(e);
    }

    //Enables/Disables scrolling through touch interaction with the RecyclerView directly
    public void setScrollingActive(boolean isScrollingActive) {
        this.isScrollingActive = isScrollingActive;
    }

    //Responsible for starting a programmatic scroll
    public boolean dispatchHandlerScroll(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) e.getY();
                startNestedScroll(2);
                break;
            case MotionEvent.ACTION_MOVE:
                int dY = y - ((int)e.getY());
                dispatchNestedPreScroll(0, dY, null, null);
                dispatchNestedScroll(0, 0, 0, dY, null);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }
}
