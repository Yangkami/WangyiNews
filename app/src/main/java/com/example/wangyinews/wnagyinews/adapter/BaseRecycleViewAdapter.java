package com.example.wangyinews.wnagyinews.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

//import com.belter.btlibrary.util.ULog;

/**
 * RecycleView的Adapter，用于框架逻辑实现，具体的ViewHolder以及绑定让子类实现
 * Created by panshq on 2017/3/20.
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter {

    protected List<T> data;
    protected OnItemClickListener mOnItemClickListener = null;
    protected OnItemLongClickListener mOnItemLongClickListener = null;
    private BaseRecycleViewAdapter adapter;
    public BaseRecycleViewAdapter(List<T> data){
        this.data = data;
        adapter = this;
    }
    public BaseRecycleViewAdapter(){
        adapter = this;
    }


    public void  setModel(List<T> data){
        this.data = data;
    }

    public List<T> getModel() {
        return data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(loadLayoutId(), parent, false);
        BaseViewHolder mBaseHolder = initViewHolder(view);
        mBaseHolder.setmListener(mOnItemClickListener);
        mBaseHolder.setmLongClickListener(mOnItemLongClickListener);
        return mBaseHolder;
    }

    /**
     * 加载layout文件
     * @return
     */
    protected abstract int loadLayoutId();

    /**
     * 初始化ViewHolder，必须继承BaseViewHolder
     * @param view
     * @return
     */
    protected abstract BaseViewHolder initViewHolder(View view);


    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }


    /**
     * 拖拽Item
     * @param from
     * @param to
     */
    public void drag(int from, int to){
        T moveItem = data.get(from);
        data.remove(from);
        data.add(to, moveItem);
        notifyItemMoved(from, to);
    }

    /**
     * 添加Item
     * @param t
     * @param position
     */
    public void addItemByPosition(T t, int position){
        data.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 移除Item
     * @param position
     */
    public void removeItemByPosition(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener mLongClickListener){
        this.mOnItemLongClickListener = mLongClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T t, int position);
    }
    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T t, int position, ImageView iv);
    }

    /**
     * RecycleView的ViewHolder基类，用于基类实现
     */
    public class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        protected OnItemClickListener mListener;
        protected OnItemLongClickListener mLongClickListener;
        public BaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        /**
         * 添加Item,为防止View直接操作Model，调用Adapter来操作Model
         * @param t
         * @param position
         */
        public void addItem(T t, int position){
            adapter.addItemByPosition(t, position);
        }

        /**
         * 移除Item，为防止View直接操作Model，调用Adapter来操作Model
         * @param position
         */
        public void removeItem(int position){
            adapter.removeItemByPosition(position);
        }

        public OnItemClickListener getmListener() {
            return mListener;
        }

        public void setmListener(OnItemClickListener mListener) {
            this.mListener = mListener;
        }

        public OnItemLongClickListener getmLongClickListener() {
            return mLongClickListener;
        }

        public void setmLongClickListener(OnItemLongClickListener mLongClickListener) {
            this.mLongClickListener = mLongClickListener;
        }

        @Override
        public void onClick(View v) {
            Log.i("aaaaaaaa","-----BaseViewHolder---onClick--------");
            if(mListener!=null){
                int position = getPosition();
                mListener.onItemClick(v, data.get(position),position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i("aaaaaaaa","-----BaseViewHolder---onLongClick--------");
            if(mLongClickListener!=null){
                int position = getPosition();
                mLongClickListener.onItemLongClick(v, data.get(position),position, null);
            }
            return true;//屏蔽onClick事件
        }
    }





}
