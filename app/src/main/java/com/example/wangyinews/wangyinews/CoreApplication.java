package com.example.wangyinews.wangyinews;

import android.app.Application;
import android.util.Log;

/**
 * Created by silent night on 2018/7/3 0003.
 */

public class CoreApplication extends Application {
    private  static  final String TAG="CoreApplication";
    private static  CoreApplication instance;
    @Override
    public void onCreate(){
        super.onCreate();

        instance=this;


        Log.i(TAG,"----CoreApplication----");



    }

    public static CoreApplication getInstance() {
        return instance;
    }

    public static void setInstance(CoreApplication instance) {
        CoreApplication.instance = instance;
    }
}
