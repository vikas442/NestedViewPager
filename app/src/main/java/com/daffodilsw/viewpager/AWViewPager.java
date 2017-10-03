package com.daffodilsw.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by daffolap-402 on 27/9/17.
 */

public class AWViewPager extends ViewPager {
    private boolean mPagingEnabled=true;

    public AWViewPager(Context context) {
        super(context);
    }

    public AWViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mPagingEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mPagingEnabled && super.onInterceptTouchEvent(ev);
    }

    public boolean isPagingEnabled() {
        return mPagingEnabled;
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        mPagingEnabled = pagingEnabled;
    }
}
