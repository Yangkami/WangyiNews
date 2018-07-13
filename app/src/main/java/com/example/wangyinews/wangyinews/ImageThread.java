package com.example.wangyinews.wangyinews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by silent night on 2018/7/13 0013.
 */

public class ImageThread extends Thread {
    String image_path;
    ImageView imageView;
    Handler handler;
    public ImageThread(String image_path, Handler handler, ImageView imageView) {
        this.image_path = image_path;
        this.imageView=imageView;
        this.handler=handler;
    }


    @Override
    public void run() {
        try {
            final Bitmap bitmap= BitmapFactory.decodeStream(new URL(image_path).openStream());//简单粗暴，可能有问题，自己做的时候注意
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}