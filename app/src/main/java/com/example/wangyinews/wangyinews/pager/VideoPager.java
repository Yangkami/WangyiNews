package com.example.wangyinews.wangyinews.pager;

import android.content.Context;

import com.example.wangyinews.wangyinews.R;

public class VideoPager extends BasePager {

    public VideoPager(Context context) {
        super(context);
    }


    @Override
    protected int loadLayoutById() {
        return R.layout.pager_video;
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initData(Object data) {
        tvCenter.setText("VideoPager");

    }
}
