package com.example.wangyinews.wangyinews.pager;

import android.content.Context;

import com.example.wangyinews.wangyinews.R;

/**
 * Created by silent night on 2018/7/4 0004.
 */

public class MinePager extends BasePager {
    public MinePager(Context context) {
        super(context);
    }

    @Override
    protected int loadLayoutById() {
        return R.layout.testlayout;

    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData(Object data) {
        tvCenter.setText("MinePager");
    }
}