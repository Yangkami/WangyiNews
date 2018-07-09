package com.example.wangyinews.wnagyinews.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.lizhixw.lizhixw.R;
import com.example.lizhixw.lizhixw.adapter.MainPagerAdapter;
import com.example.lizhixw.lizhixw.pager.BasePager;
import com.example.lizhixw.lizhixw.pager.LivePager;
import com.example.lizhixw.lizhixw.pager.MinePager;
import com.example.lizhixw.lizhixw.pager.NewsPager;
import com.example.lizhixw.lizhixw.pager.RadioPager;
import com.example.lizhixw.lizhixw.pager.VideoPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private  static final String TAG="";
    private ViewPager vpMain;
    private RadioGroup rgMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"-----");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpMain=findViewById(R.id.vp_main);
        rgMain=findViewById(R.id.rg_main);

        rgMain.setOnCheckedChangeListener(this);
        vpMain.setOnPageChangeListener(this);

        //rgMain.setOnCheckedChangeListener(this);

        initData();

    }
    private void initData(){
        List<BasePager> pagers=new ArrayList<>();
        pagers.add(new NewsPager(this));
        pagers.add(new VideoPager(this));
        pagers.add(new LivePager(this));
        pagers.add(new RadioPager(this));
        pagers.add(new MinePager(this));

        //适配器
        MainPagerAdapter mAdapter = new MainPagerAdapter(pagers);
        //配置适配器

        vpMain.setAdapter(mAdapter);

        pagers.get(0).initData(null);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = -1;
        switch (checkedId){
            case R.id.btn_news:
                index =0;
                break;
            case R.id.btn_video:
                index =1;
                break;
            case R.id.btn_live:
                index =2;
                break;
            case R.id.btn_audio:
                index =3;
                break;
            case R.id.btn_mine:
                index =4;
                break;
        }
        Log.i(TAG,"---index=" +index);
        if(index>=0){
            vpMain.setCurrentItem(index);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.w(TAG,"----------onPageScrolled"+position);

    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG,"----------posotoion"+position);
        int resid=rgMain.getChildAt(position).getId();
        rgMain.check(resid);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
