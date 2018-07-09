package com.example.wangyinews.wnagyinews;

import android.app.Application;
import android.util.Log;

/**
 * Created by silent night on 2018/7/3 0003.
 */

public class ApplicationHelper extends Application {
    private  static  final String TAG="ApplicationHelper";
    @Override
    public void onCreate(){
        super.onCreate();

        Log.i(TAG,"----ApplicationHelper----");
    }
}
