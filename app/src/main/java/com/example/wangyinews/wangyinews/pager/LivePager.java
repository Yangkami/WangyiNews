package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wangyinews.wangyinews.Bean.LiveBean;
import com.example.wangyinews.wangyinews.LocalVideoActivity;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.VideoListActivity;
import com.example.wangyinews.wangyinews.adapter.TextImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class LivePager extends BasePager {
    public List<LiveBean> list;
    ListView listView=null;
    public LivePager(Context context) {
        super(context);
    }



    @Override
    protected int loadLayoutById() {
        return R.layout.pager_live;
    }

    @Override
    public void initViews() {
        list=new ArrayList<>();
        list.add(new LiveBean("江里好多鱼", mContext.getResources().getDrawable(R.drawable.new1)));
        list.add(new LiveBean("今日头条", mContext.getResources().getDrawable(R.drawable.new2)));
        list.add(new LiveBean("西瓜", mContext.getResources().getDrawable(R.drawable.new3)));
        list.add(new LiveBean("网易新闻", mContext.getResources().getDrawable(R.drawable.new4)));
        list.add(new LiveBean("新闻", mContext.getResources().getDrawable(R.drawable.new5)));

        listView= mRootView.findViewById(R.id.list);
        TextImageAdapter textImageAdapter = new TextImageAdapter(mContext,list);
        listView.setAdapter(textImageAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext.getApplicationContext(),"跳转至直播间！",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(mContext,LocalVideoActivity.class);//跳转
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void initData(Object data) {
//        tvCenter.setText("LivePager");
    }
}
