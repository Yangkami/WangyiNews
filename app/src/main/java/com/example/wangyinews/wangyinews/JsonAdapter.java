package com.example.wangyinews.wangyinews;

/**
 * Created by silent night on 2018/7/13 0013.
 */

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CAPTON on 2016/11/25.
 */
// 适配器，等待被JsonThread调用

public class JsonAdapter extends BaseAdapter {

    List<NewsBean> newsBeans;
    //Context context;
    Context mContext;
    LayoutInflater inflater;
    Handler handler;

    public JsonAdapter(Context mContext, Handler handler, List<NewsBean> newsBeans) {
        this.handler=handler;
        this.mContext=mContext;
        this.newsBeans = newsBeans;
        inflater= LayoutInflater.from(mContext);//从pager中上下文对象中获取LayoutInflater；所以说这个context,和handler对象很重要，贯穿整项目
    }

    @Override
    public int getCount() {
        return newsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return newsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //重写getView方法，即设置ListView每一项的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;

        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_news_pager,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);//设置tag
        }else {
            holder= (ViewHolder) convertView.getTag(); //获取tag
        }
        //System.out.println(String.valueOf(newsBeans.get(position).news_id));//测试数据是否正常
        holder.news_id.setText(String.valueOf(newsBeans.get(position).news_id));
        holder.image_id.setText(newsBeans.get(position).image_id);
        //System.out.println(newsBeans.get(position).image_id);

        holder.image_id.setText(newsBeans.get(position).news_kind);
        holder.image_id.setText(newsBeans.get(position).news_title);
        holder.image_id.setText(newsBeans.get(position).user_id);
        holder.image_id.setText(newsBeans.get(position).good_num);
        holder.image_id.setText(newsBeans.get(position).see_num);
        holder.image_id.setText(newsBeans.get(position).comment_num);
        holder.image_id.setText(newsBeans.get(position).news_submit_time);
        holder.image_id.setText(newsBeans.get(position).news_content);



        new ImageThread(newsBeans.get(position).image_path, handler,holder.image).start();//开启新线程下载图片并在新线程中更新UI，所以要传递handler对象
        return convertView;
    }

    //用于暂时保存视图对象
    class ViewHolder{
        public TextView image_id;
        public TextView news_id;
        public ImageView image;
        public TextView tvtitle;

        public ViewHolder(View view){
            image_id= (TextView) view.findViewById(R.id.name);
            news_id= (TextView) view.findViewById(R.id.age);
            image= (ImageView) view.findViewById(R.id.imageView);
            tvtitle=(TextView)view.findViewById(R.id.tv_title);
        }
    }
}