package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.adapter.NewsLvAdapter;

import java.util.ArrayList;
import java.util.List;


//import butterknife.ButterKnife;

/**
 * 主页左侧页面父类
 * Created by pan on 2017/3/4.
 */
public class BaseNewsPager {

    public static final String TAG = "BaseNewsPager";

    public Context mContext;
    public View mRootView;// 布局对象


    protected LayoutInflater lf;
    private ListView lvNews;


    public BaseNewsPager(Context context) {
        mContext = context;
        lf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = lf.inflate(loadLayoutById(), null);
//        ButterKnife.bind(mRootView);


        initView();
    }

    protected int loadLayoutById() {
        return R.layout.pager_news_tag;
    }

    private void initView() {
//        tvCenter = mRootView.findViewById(R.id.tv_center_main);
        //加载子类view
        initViews();
    }

    /**
     * 初始化布局
     */
    public void initViews() {

        lvNews = mRootView.findViewById(R.id.lv_news_pager);

        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add("item" + i);
        }

        NewsLvAdapter lvAdapter = new NewsLvAdapter(items);
        lvNews.setAdapter(lvAdapter);
    }

    public void initData(Object data) {

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