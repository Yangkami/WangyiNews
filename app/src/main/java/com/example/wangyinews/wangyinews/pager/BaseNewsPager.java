package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.wangyinews.wangyinews.Bean.NewsBean;
import com.example.wangyinews.wangyinews.activity.DetailActivity;
import com.example.wangyinews.wangyinews.activity.FreeActivity;
import com.example.wangyinews.wangyinews.activity.MainActivity;
import com.example.wangyinews.wangyinews.adapter.JsonThread;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.adapter.NewsLvAdapter;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

//import butterknife.ButterKnife;

/**
 * 主页左侧页面父类
 * Created by pan on 2017/3/4.
 */
public class BaseNewsPager {

    public static final String TAG = "BaseNewsPager";
    TextView tv1;
    TextView tv2 ;
    TextView tv3 ;
    ImageView iv1 ;
    public Context mContext;
    public View mRootView;// 布局对象

    Handler handler;
    String url="http://112.74.165.209:3020/selectAllNews";//url。


    protected LayoutInflater lf;
    private ListView lvNews;


    public BaseNewsPager(Context context) {
        mContext = context;
        lf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = lf.inflate(loadLayoutById(), null);
//        ButterKnife.bind(mRootView);


        initView();
        initData();
    }

    protected int loadLayoutById() {
        return R.layout.pager_news_tag;
    }

    private void initView() {
        //加载子类view
        initViews();
    }

    /**
     * 初始化布局
     */
    public void initViews() {

        lvNews = mRootView.findViewById(R.id.lv_news_pager);


//        List<String> items = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            //items.add("item" + i);
//        }
//
//        NewsLvAdapter lvAdapter = new NewsLvAdapter(items);
//        lvNews.setAdapter(lvAdapter);
    }

    public void initData() {
        handler=new Handler();
        new JsonThread(mContext, lvNews, url,handler).start();

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {

                NewsBean data = (NewsBean) arg0.getItemAtPosition(position);

                Bundle bundle = new Bundle();
//                bundle.putInt("id",position);
                bundle.putCharSequence("title",data.news_title);
                bundle.putCharSequence("text",data.news_content);
                bundle.putCharSequence("time",data.news_submit_time);
                bundle.putCharSequence("image_path",data.image_path);
                Intent intent=new Intent(mContext,DetailActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }


    /***
     * 无数据默认加载
     */
    public void loadViews() {

    }


    public String getResString(int resId) {
        return mContext.getResources().getString(resId);
    }


}