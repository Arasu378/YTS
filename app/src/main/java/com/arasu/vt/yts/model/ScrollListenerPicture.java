package com.arasu.vt.yts.model;

/**
 * Created by kyros on 03-10-2017.
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by Thirunavukkarasu on 01-11-2016.
 */

public abstract class ScrollListenerPicture extends RecyclerView.OnScrollListener {
    public static String TAG=ScrollListenerPicture.class.getSimpleName();
    private int previousTotal=0;
    private boolean loading=true;
    private int visibleThreshold=5;
    int firstvisibleitem,visibleitemcount,totalitemcount;
    private int current_page=1;
    private LinearLayoutManager mLinearLayoutManager;
    RecyclerView.LayoutManager mLayoutManager;

    public ScrollListenerPicture(LinearLayoutManager linearLayoutManager){
        this.mLinearLayoutManager=linearLayoutManager;
    }
    public ScrollListenerPicture(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
        Log.d("visibleThreshold",""+visibleThreshold);
    }
    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);

        }
        visibleitemcount=recyclerView.getChildCount();
        totalitemcount=mLinearLayoutManager.getItemCount();
        firstvisibleitem=mLinearLayoutManager.findFirstVisibleItemPosition();
        if(loading){
            if(totalitemcount>previousTotal){
                loading=false;
                previousTotal=totalitemcount;
            }
        }
        if(!loading&&(totalitemcount-visibleitemcount)<=(firstvisibleitem+visibleThreshold)){
            current_page++;
            onLoadMore(current_page);
            loading=true;
        }



    }
    public abstract void onLoadMore(int current_page);
    public void reset(int previousTotal, boolean loading) {
        this.previousTotal = previousTotal;
        this.loading = loading;
    }
}
