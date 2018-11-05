package com.feng.animatordemo;

import android.view.View;

/**
 * @author Feng Zhaohao
 * Created on 2018/11/5
 */
public class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View mTarget) {
        this.mTarget = mTarget;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
