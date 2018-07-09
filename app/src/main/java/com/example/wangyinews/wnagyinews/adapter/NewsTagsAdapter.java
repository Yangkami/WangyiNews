package com.example.wangyinews.wnagyinews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lizhixw.lizhixw.R;

import java.util.List;

/**
 * Created by silent night on 2018/7/5 0005.
 */

public class NewsTagsAdapter extends BaseRecycleViewAdapter {

    public  NewsTagsAdapter(List<String> tags){
        this.data=tags;
    }
    @Override
    protected int loadLayoutId() {
        return R.layout.tv_news_pager;
    }

    @Override
    protected BaseViewHolder initViewHolder(View view) {
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsViewHolder mHolder=(NewsViewHolder)holder;
        String tag=(String)data.get(position);
        mHolder.tv.setText(tag);
    }
    private class NewsViewHolder extends BaseViewHolder{

        private TextView tv;

        public NewsViewHolder(View itemView) {
            super(itemView);
            initview(itemView);
        }

        private void initview(View itemView) {
            tv=itemView.findViewById(R.id.tv_tag_news_pager);

        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getPosition();
                mListener.onItemClick(v, data.get(position), position);
            }
        }
    }
}
