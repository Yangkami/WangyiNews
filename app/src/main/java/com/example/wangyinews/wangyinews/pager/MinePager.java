package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.wangyinews.wangyinews.activity.LoginRegisterActivity;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.activity.FreeActivity;
import com.example.wangyinews.wangyinews.activity.GoldActivity;
import com.example.wangyinews.wangyinews.activity.JDActivity;
import com.example.wangyinews.wangyinews.activity.ScanCodeActivity;


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
        Log.i(TAG,"-----MinePager----initView-------------");

        mRootView.findViewById(R.id.btn_denglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG,"---------ONCLICK-----------");
                Intent intent=new Intent(mContext,LoginRegisterActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mContext.startActivity(intent);
            }
        });
        mRootView.findViewById(R.id.ll_gold_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,GoldActivity.class);
                mContext.startActivity(intent);
            }
        });
        mRootView.findViewById(R.id.ll_jd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,JDActivity.class);
                mContext.startActivity(intent);
            }
        });
        mRootView.findViewById(R.id.ll_free).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,FreeActivity.class);
                mContext.startActivity(intent);
            }
        });

        mRootView.findViewById(R.id.tv_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ScanCodeActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void initData(Object data) {
        //tvCenter.setText("MinePager");

        Log.i(TAG,"-----MinePager----initData-------------");

    }
}