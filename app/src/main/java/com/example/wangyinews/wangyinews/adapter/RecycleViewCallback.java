package com.example.wangyinews.wangyinews.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;


/**
 * RecycleView侧滑拖拽Callback的实现类
 * Created by panshq on 2017/3/19.
 */
public class RecycleViewCallback<T> extends ItemTouchHelper.Callback {

    /**是否要拖动Item，默认为要拖动**/
    private boolean isDragItem = true;
    /**是否要移除Item，默认为不要**/
    private boolean isRemoveItem = false;

    /**
     * 设置拖拽和侧滑方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0, swipeFlags = 0;
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        // 设置拖拽方向
        int orientation = 0;
        if (lm instanceof GridLayoutManager) {//网格布局
            orientation = ((LinearLayoutManager) lm).getOrientation();
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else if (lm instanceof StaggeredGridLayoutManager) {//交错网格布局
            orientation = ((StaggeredGridLayoutManager) lm).getOrientation();
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else if (lm instanceof LinearLayoutManager) {//线性布局
            orientation = ((LinearLayoutManager) lm).getOrientation();
            if (orientation == OrientationHelper.VERTICAL) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            } else {
                dragFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            }
        }
        //设置侧滑方向
        if (orientation == OrientationHelper.VERTICAL) {
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        } else {
            swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        //不允许拖拽
        if(!isDragItem){
            dragFlags = 0;
        }
        // 不允许侧滑
        if(!isRemoveItem){
            swipeFlags = 0;
        }


        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖拽中，更新回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        if (isDragItem) {
            //拿到适配器，拖拽Item的工作交给BaseRecycleViewAdapter完成
            BaseRecycleViewAdapter adapter = (BaseRecycleViewAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.drag(from, to);
            }
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (isRemoveItem) {
            //将holder强转成BaseViewHolder，移除Item的工作交给BaseViewHolder完成
            if(viewHolder!=null) {
                int position = viewHolder.getAdapterPosition();
                BaseRecycleViewAdapter.BaseViewHolder holder = (BaseRecycleViewAdapter.BaseViewHolder) viewHolder;
                if (holder != null) {
                    holder.removeItem(position);
                }
            }
        }
    }


    public void setIsDragItem(boolean isDragItem) {
        this.isDragItem = isDragItem;
    }

    public void setIsRemoveItem(boolean isRemoveItem) {
        this.isRemoveItem = isRemoveItem;
    }


}
