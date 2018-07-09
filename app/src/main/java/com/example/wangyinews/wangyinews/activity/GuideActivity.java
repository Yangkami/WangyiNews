package com.example.wangyinews.wangyinews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.adapter.GuidePagerAdapter;
import com.example.wangyinews.wangyinews.helper.SharePrefrenceHelper;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final String TAG = "";
    private ViewPager vpGuide;
    private LinearLayout llPoint;
    private  int[]imgids=new int[]{R.drawable.b01,R.drawable.b02,R.drawable.b03,R.drawable.b04_1080x1920};
    private View point;
    private int mPointWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        //设置全屏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide);
        vpGuide=findViewById(R.id.vp_guide);

        llPoint=findViewById(R.id.ll_point_guide);
        point=findViewById(R.id.iv_point_blue_guide);


        vpGuide.setOnPageChangeListener(this);
        //findViewById(R.id.btn_start).setOnClickListener(this);

        initData();
    }
    private void initData(){

        List<View> views=new ArrayList<>();

        LayoutInflater lif=getLayoutInflater();

        ImageView iv=null;
        View mView =null;
        final int count=imgids.length;
        for (int i=0;i<count;i++){
            mView =lif.inflate(R.layout.pager_guide,null);
            iv =mView.findViewById(R.id.iv_guide);
            iv.setBackgroundResource(imgids[i]);


            if (i==count-1){
                Button btnSart=mView.findViewById(R.id.btn_start_guide);
                btnSart.setVisibility(View.VISIBLE);
                btnSart.setOnClickListener(this);
            }
            views.add(mView);

            //添加白色圆点

            View view=lif.inflate(R.layout.iv_point_guide,null);
            llPoint.addView(view);

        }

        //适配器
        GuidePagerAdapter mAdapter = new GuidePagerAdapter(views);
        //配置适配器
        vpGuide.setAdapter(mAdapter);

        // 获取视图树, 对layout结束事件进行监听
        llPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        llPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        //算出点与点之间的间距（从中心算起）
                        mPointWidth = llPoint.getChildAt(1).getLeft() - llPoint.getChildAt(0).getLeft();

                    }
                });

//        point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                int len = llPoint.getChildAt(0).getLeft();
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) point.getLayoutParams();// 获取当前红点的布局参数
//                params.leftMargin = len;// 设置左边距
//
//                point.setLayoutParams(params);// 重新给小蓝点设置布局参数
//            }
//        });


    }


    /**
     * 跳转
     */
    private void gotoMainActivity(){
        Intent mintent=new Intent(this,MainActivity.class);
        startActivity(mintent);

        SharePrefrenceHelper sph=new SharePrefrenceHelper(getApplicationContext());
        sph.open("first_run");
        sph.putBoolean("is_not_first_run",true);

        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_guide:
            gotoMainActivity();
            break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG,"-----------------onPageScrolled------------------------");

        // System.out.println("当前位置:" + position + ";百分比:" + positionOffset
        // + ";移动距离:" + positionOffsetPixels);
        int len=(int)(mPointWidth*positionOffset)+position*mPointWidth;
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)point.getLayoutParams();//获取当前红点布局参数
        params.leftMargin=len;//设置左边距
        point.setLayoutParams(params);// 重新给小蓝点设置布局参数
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
