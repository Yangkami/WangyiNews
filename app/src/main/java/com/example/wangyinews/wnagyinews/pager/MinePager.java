package com.example.wangyinews.wnagyinews.pager;

import android.content.Context;

import com.example.lizhixw.lizhixw.R;

/**
 * Created by silent night on 2018/7/4 0004.
 */

public class MinePager extends BasePager {
    public MinePager(Context context) {
        super(context);
    }

    @Override
    protected int loadLayoutById() {
        return R.layout.pager_mine;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData(Object data) {
        tvCenter.setText("MinePager");
    }
}