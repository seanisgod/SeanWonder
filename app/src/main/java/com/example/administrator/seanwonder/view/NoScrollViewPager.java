package com.example.administrator.seanwonder.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sean on 2016/11/2.
 */

public class NoScrollViewPager extends ViewPager {
    private float xDistance, xLast;
    private boolean noScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = 0f;
                xLast = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                //第一个Fragment禁止右滑
                if (xLast - curX < 0 && getCurrentItem() == 0) {
                    return false;
                }
                //最后一个Fragment禁止左滑
//                if (xLast - curX > 0 && getCurrentItem() == 1) {
//                    return false;
//                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    /**
     * 控制Viewpager是否可滑动
     *
     * @param noScroll : true 不能滑动
     */

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

}
