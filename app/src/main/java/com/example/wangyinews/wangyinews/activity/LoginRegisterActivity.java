package com.example.wangyinews.wangyinews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.UserBean;
import com.example.wangyinews.wangyinews.info.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_register,tv_login;
    private ImageView im_change1,im_change2;
    private RelativeLayout mview1=null,mview2=null;
    private EditText username,email,iphone,password;
    private EditText useraccount,password1;
    private static final  String TAG="LoginRegisterActivity";
    Button btn_register,btn_login;
    private int Flag=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_login);

        tv_login=findViewById(R.id.tv_login);  //获取登陆切换
        tv_register=findViewById(R.id.tv_register);//获取注册切换
        im_change1=findViewById(R.id.im_change1);//小按钮切换
        im_change2=findViewById(R.id.im_change2);

        mview1= findViewById(R.id.rl1);      //登陆注册容器
        mview2=findViewById(R.id.rl3);


        username=findViewById(R.id.username);   //获取用户注册输入
        email=findViewById(R.id.email);
        iphone=findViewById(R.id.phone);
        password=findViewById(R.id.password);

        useraccount=findViewById(R.id.username_login);//获取用户登陆输入
        password1=findViewById(R.id.password1);

        btn_register=findViewById(R.id.bt_sign_up);  //登陆注册点击触发事件
        btn_login=findViewById(R.id.bt_login);


        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        im_change1.setOnClickListener(this);
        im_change2.setOnClickListener(this);
        tv_login.setClickable(true);
        tv_register.setClickable(true);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {    //切换登陆与注册界面，登陆/注册提交，系统调用
                switch(view.getId()){
                    case R.id.tv_login:
                        mview1.setVisibility(View.GONE);
                        mview2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.tv_register:
                        mview1.setVisibility(View.VISIBLE);
                        mview2.setVisibility(View.GONE);
                        break;
                    case R.id.im_change1:
                        mview1.setVisibility(View.GONE);
                        mview2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.im_change2:
                        mview1.setVisibility(View.VISIBLE);
                        mview2.setVisibility(View.GONE);
                        break;
                    case  R.id.bt_sign_up:
                        Log.i(TAG,"------bt_sign_up----");
                        httpPost();
                        break;
                    case R.id.bt_login:
                        Log.i(TAG,"------bt_login----");
                        httpPost1();
                        break;
                    /*case R.id.bt_sign_up1:
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//可选
                        ComponentName cn = new ComponentName(package_name,activity_path);
                        intent.setComponent(cn);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        } else {
                            //找不到指定的 Activity
                        }
                        break;*/
        }
}


    public void httpPost() {               //注册请求
        String url = "http://112.74.165.209:3020/register";
//        UserBeanRegister userBeanRegister=new UserBeanRegister(username.getText().toString(),password.getText().toString(),iphone.getText().toString(),email.getText().toString());
//        String json = new Gson().toJson(userBeanRegister);
//        Log.i(TAG, "---httpPost----json = " + json);
        FormBody body = new FormBody.Builder()     //提交注册请求
                .add("username", username.getText().toString())
                .add("password", password.getText().toString())
                .add("iphone", iphone.getText().toString())
                .add("email", email.getText().toString())
                .build();


        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "----onFailure-------e: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//注册返回请求
                String jstring = response.body().string();
                Log.i(TAG, "-----onResponse-------Response: " + jstring);
                try {
                    JSONObject jo=new JSONObject(jstring);
                    int flag =jo.getInt("flag");
                    Log.i(TAG,"---onResponse----flag = "+ flag);
                    Flag=flag;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Flag==1){
                                Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(LoginRegisterActivity.this,MainActivity.class);//跳转
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"注册失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Flag=0;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
            }
        });

    }


    public void httpPost1() {   //登录请求
        String url = "http://112.74.165.209:3020/login";
//       UserBeanLogin userBeanLogin=new UserBeanLogin(useraccount.getText().toString(),password1.getText().toString());
//       String json = new Gson().toJson(userBeanLogin);
//        Log.i(TAG, "---httpPost----json = " + json);
        FormBody body = new FormBody.Builder()
                .add("useraccount", username.getText().toString())
                .add("password", password1.getText().toString())
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "----onFailure-------e: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jstring = response.body().string();
                Log.i(TAG, "-----onResponse-------Response: " + jstring);
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();

                try {
                    JSONObject jo = new JSONObject(jstring);
                    int flag = jo.getInt("flag");
                    JSONObject userData = jo.getJSONObject("userData");
                    Log.i(TAG, "---onResponse----flag = " + flag);
                    Log.i(TAG, "---onResponse----userData = " + userData.toString());

                    String name = "";
                    String level = "";
                    UserBean bean = new UserBean(name,level);
                    UserInfo.getInstance().setUserBean(bean);
                    Flag = flag;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Flag == 1) {
                                Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);//跳转
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    for (int i = 0; i < headers.size(); i++) {
                        Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    private void onReceivedDataGson(String jstring) {
//        Log.i(TAG, "-------onReceivedDataGson----------- ");
//        Gson gson = new Gson();
//        //解析整个对象，遍历数组，注意：嵌套对象必须Serializable或Parceable
//        List<UserBean> users = null;
//        try {
//            JSONArray array = new JSONArray(jstring);
//            Match match = null;
//            users = new ArrayList<>();
//            final int len = array.length();
//            for (int j = 0; j < len; j++) {
//                match = gson.fromJson(array.get(j).toString(), Match.class);
//
//                users.add(match);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        for (Match match : users) {
//            Log.i(TAG, "-------onReceivedDataGson-----------match = " + match);
//        }
//    }
//        });

}