package com.example.wangyinews.wangyinews.pager;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wangyinews.wangyinews.R;


//import butterknife.ButterKnife;

/**
 * 主页左侧页面父类
 * Created by pan on 2017/3/4.
 */
public abstract class BasePager{

    public static final String TAG = "BasePager";

    public Context mContext;

    public View mRootView;// 布局对象
    protected TextView tvCenter;


    protected LayoutInflater lf;


    public BasePager(Context context) {
        mContext = context;
        lf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = lf.inflate(loadLayoutById(), null);

//        ButterKnife.bind(mRootView);


        initView();
    }

    protected int loadLayoutById() {

        return R.layout.pager_main;
    }

    private void initView() {
//        tvCenter = mRootView.findViewById(R.id.tv_center_main);
        //加载子类view
        initViews();
    }

    /**
     * 初始化布局
     */
    public abstract void initViews();

    public abstract void initData(Object data);


    /***
     * 无数据默认加载
     */
    public void loadViews() {

    }


    public String getResString(int resId) {
        return mContext.getResources().getString(resId);
    }


}
