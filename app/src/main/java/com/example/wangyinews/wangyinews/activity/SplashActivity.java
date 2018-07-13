package com.example.wangyinews.wangyinews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.helper.SharePrefrenceHelper;

public class SplashActivity extends AppCompatActivity {
    private static final int DELAY=3000;
    private static final int TO_GUIDE=1010;
    private static final String URL="http://www.cnr.cn/mthz/20180711/W020180711523528710841.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        //设置全屏<=4.0
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        ImageView ivSplash=findViewById(R.id.iv_splash);
        //Picasso.with(this.getApplicationContext()).load(URL).into(ivSplash);

        Glide.with(this.getApplicationContext()).load(URL).into(ivSplash);
    }
    @Override
    protected  void onResume(){
        super.onResume();
        mHandler.sendEmptyMessageDelayed(TO_GUIDE,DELAY);

       // SharePrefrenceHelper sph=new SharePrefrenceHelper(getApplicationContext());
        //sph.open("first_run");


    }
    private void gotoGuideActivity(){
        Intent mintent=new Intent(this,GuideActivity.class);
        startActivity(mintent);
        finish();
    }
    private void gotoMainActivity(){
        Intent mintent=new Intent(this,MainActivity.class);
        startActivity(mintent);
        finish();
    }

    //延时启动引导页
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case TO_GUIDE:
                    SharePrefrenceHelper sph = new SharePrefrenceHelper(getApplicationContext());
                    sph.open("first_run");
                    boolean isNotFirstRun = sph.getBoolean("is_not_first_run");
                    if (!isNotFirstRun) {//第一次启动，跳转到引导页
                        gotoGuideActivity();
                    } else {//跳到专业
                        gotoMainActivity();
                    }

                    break;
            }
        }
    };
}
