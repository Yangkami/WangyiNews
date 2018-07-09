package com.example.wangyinews.wangyinews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyinews.wangyinews.R;

import java.util.List;

/**
 * Created by silent night on 2018/7/5 0005.
 */

public class NewsLvAdapter extends BaseAdapter {
    private List<String> data;

    public  NewsLvAdapter(List<String> data){
        this.data=data;
    }
    @Override
    public int getCount() {
        if(data==null){
            return 0;
        }
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_pager,null);

            holder.ivNews = convertView.findViewById(R.id.iv_item_news_pager);
            holder.tvTitle = convertView.findViewById(R.id.tv_title_item_news_pager);
            holder.tvSource = convertView.findViewById(R.id.tv_source_item_news_pager);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tvTitle.setText(data.get(position));
        holder.tvSource.setText(data.get(position));
        return convertView;
    }
    class ViewHolder{
        private ImageView ivNews;
        private TextView tvTitle;
        private TextView tvSource;
    }
}
