package com.example.wangyinews.wangyinews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangyinews.wangyinews.R;

/**
 * Created by silent night on 2018/7/11 0004.
 * about webview
 */
public class FreeActivity extends AppCompatActivity {

    public FreeActivity(){
        // TODO 自动生成的构造函数存根
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView web=(WebView) findViewById(R.id.web);
        web.loadUrl("https://c.m.163.com/nc/qa/newsapp/free-traffic.html");

        web.getSettings().setJavaScriptEnabled(true);


        web.getSettings().setUseWideViewPort(true);//关键点
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//全


        web.setWebViewClient(new WebViewClient(){
            public boolean shouldOverideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });
    }
}
