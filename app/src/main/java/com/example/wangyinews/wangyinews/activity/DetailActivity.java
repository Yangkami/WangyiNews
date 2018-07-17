package com.example.wangyinews.wangyinews.activity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyinews.wangyinews.Bean.NewsBean;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.adapter.JsonThread;

import java.util.List;

public class DetailActivity extends AppCompatActivity {


    TextView tvtitle;
    TextView tvtime;
    TextView tvtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Bundle bundle=getIntent().getExtras();
        //int position=bundle.getInt("id");
        CharSequence title = bundle.getCharSequence("title");
        CharSequence text = bundle.getCharSequence("text");
        CharSequence time = bundle.getCharSequence("time");
        CharSequence imagePath = bundle.getCharSequence("image_path");
        Log.i("test",title.toString());
        tvtitle=(TextView) findViewById(R.id.tv_detail_title);
        tvtitle.setText(title);
        tvtime=(TextView) findViewById(R.id.tv_news_submit_time);
        tvtime.setText(time);
        tvtext=(TextView) findViewById(R.id.tv_news_content);
        tvtext.setText("      "+text);
    }
}
