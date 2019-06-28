package com.dasong.common.callback;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.AbsListView;

/**
 * 兼容ListView和RecyclerView的滚动监听，上下滚动的列表可监听滚动到顶部和滚动到底部
 */
public class SimpleListScollListener extends RecyclerView.OnScrollListener implements AbsListView.OnScrollListener {

    public static final int NO_STATE = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private Callback callback;
    private int scollState;

    private boolean hasHeader;
    private boolean hasFooter;
    private boolean hasCallHeader = false;
    private boolean hasCallFooter = false;

    private int firstCompletelyVisibleItemPosition = -1;

    public SimpleListScollListener(Callback callback){
        if(callback == null){
            throw new NullPointerException("未设置Callback");
        }
        this.callback = callback;
    }

    /**
     * @param callback
     * @param hasHeader
     * @param hasFooter
     */
    private SimpleListScollListener(PullCallback callback, boolean hasHeader, boolean hasFooter){
        if(callback == null){
            throw new NullPointerException("未设置Callback");
        }
        this.callback = callback;
        this.hasHeader = hasHeader;
        this.hasFooter = hasFooter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if((scollState == UP) && (scrollState == SCROLL_STATE_IDLE)){
            callback.scollToTop();

        }else if((scollState == DOWN) && (scrollState == SCROLL_STATE_IDLE)){
            callback.scollToBottom();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        listenCallState(recyclerView,newState);
        topEvent(recyclerView,newState);
        bottomEvent(recyclerView,newState);
    }

    private void topEvent(RecyclerView recyclerView, int newState){
        if((scollState == UP) && (newState == SCROLL_STATE_IDLE)){
            if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int first = manager.findFirstCompletelyVisibleItemPosition();
                int head = manager.findFirstVisibleItemPosition();
                if(hasHeader){
                    PullCallback pullCallback = (PullCallback)callback;
                    if(first == 1 && head == 1){
                        callback.scollToTop();
                    }else if(first == 1 && head == 0){
                        if(!hasCallHeader){
                            pullCallback.startPullDown();
                            hasCallHeader = true;
                        }
                    }else if(head == 0 && first == 0){
                        pullCallback.scollToHeader();
                    }
                }else{
                    if(first == 0){
                        callback.scollToTop();
                    }
                }

            }
        }
    }

    private void bottomEvent(RecyclerView recyclerView, int newState){
        if((scollState == DOWN) && (newState == SCROLL_STATE_IDLE)){
            if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int last = manager.findLastCompletelyVisibleItemPosition();
                int foot = manager.findLastVisibleItemPosition();
                int count = recyclerView.getAdapter().getItemCount();
                if(hasFooter){
                    PullCallback pullCallback = (PullCallback)callback;
                    if(last == count-2 && foot == count-2){
                        callback.scollToBottom();
                    }else if(last == count-2 && foot == count-1){
                        if(!hasCallFooter){
                            pullCallback.startPullUp();
                            hasCallFooter = true;
                        }
                    }else if(last == count-1 && foot == count-1){
                        pullCallback.scollToFooter();
                    }
                }else{
                    if(last == recyclerView.getAdapter().getItemCount()-1){
                        callback.scollToBottom();
                    }
                }

            }
        }
    }

    private void listenCallState(RecyclerView recyclerView, int newState){
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if(hasHeader){
            if(scollState == DOWN && newState != SCROLL_STATE_IDLE){
                int first = manager.findFirstVisibleItemPosition();
                if(first > 0){
                    hasCallHeader = false;
                }
            }
        }
        if(hasFooter){
            if(scollState == UP && newState != SCROLL_STATE_IDLE){
                int last = manager.findLastVisibleItemPosition();
                if(last < recyclerView.getAdapter().getItemCount() - 1){
                    hasCallFooter = false;
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(hasHeader){
            if ((visibleItemCount > 0) && (firstVisibleItem == 1)) {
                if (view.getChildAt(1).getTop() >= 0) {
                    scollState = UP;
                }
            }
            else {
                scollState = NO_STATE;
            }
        }else{
            if ((visibleItemCount > 0) && (firstVisibleItem == 0)) {
                if (view.getChildAt(0).getTop() >= 0) {
                    scollState = UP;
                }
            }
            else {
                scollState = NO_STATE;
            }
        }
        if(hasFooter){
            if ((totalItemCount > 0)
                    && (view.getLastVisiblePosition() == totalItemCount - 1)) {
                if (view.getBottom() == view.getChildAt(
                        view.getChildCount() - 1).getBottom()) {
                    scollState = DOWN;
                }
            }
            else {
                scollState = NO_STATE;
            }
        }else{
            if ((totalItemCount > 0)
                    && (view.getLastVisiblePosition() == totalItemCount - 2)) {
                if (view.getBottom() == view.getChildAt(
                        view.getChildCount() - 2).getBottom()) {
                    scollState = DOWN;
                }
            }
            else {
                scollState = NO_STATE;
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //dy <0 表示 上滑， dy>0 表示下滑
        if(dy < 0){
            scollState = UP;
        }else if(dy > 0){
            scollState = DOWN;
        }else {
            scollState = NO_STATE;
        }
        if(callback instanceof ItemCallback){
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int cfcvip = manager.findFirstCompletelyVisibleItemPosition();
            if(firstCompletelyVisibleItemPosition != cfcvip){
                firstCompletelyVisibleItemPosition = cfcvip;
                ((ItemCallback)callback).firstCompletelyVisibleItemChange(firstCompletelyVisibleItemPosition);
            }

        }
    }

    public interface Callback{
        void scollToTop();
        void scollToBottom();

    }

    /**
     * 预留下拉和上拉的接口，暂未实现
     */
    public interface PullCallback extends Callback {
        //开始下拉
        void startPullDown();
        //开始上拉
        void startPullUp();
        //下拉到顶
        void scollToHeader();
        //上拉到底
        void scollToFooter();
    }

    public interface ItemCallback extends Callback{

        void firstCompletelyVisibleItemChange(int position);
    }

}
