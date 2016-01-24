package org.dalol.apkutility.model.callback;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
* Created by Rutul on 02-03-2015.
* This class is a ScrollListener for RecyclerView that allows to show/hide
* views when list is scrolled.
* It assumes that you have added a header to your list.
* */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static final int HIDE_THRESHOLD = 10;

    private int mScrolledDistance = 0;
    private boolean mControlsVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

            int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            if (firstVisibleItem == 0) {
                if (!mControlsVisible) {
                    onShow(dy);
                    mControlsVisible = true;
                }
            } else {
                if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) {
                    onHide(dy);
                    mControlsVisible = false;
                    mScrolledDistance = 0;
                } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible) {
                    onShow(dy);
                    mControlsVisible = true;
                    mScrolledDistance = 0;
                }
            }
            if ((mControlsVisible && dy > 0) || (!mControlsVisible && dy < 0)) {
                mScrolledDistance += dy;
            }
    }

    public abstract void onHide(int dy);

    public abstract void onShow(int dy);
}