package com.example.yunwen.newtext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;



public class WebActivity extends Activity {

    private static final String TAG = "测试";

    private BridgeWebView mBridgeWebView;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //实例化实体类
        Intent intent = this.getIntent();
        String email = intent.getStringExtra("email");
        mUserInfo = new UserInfo(email);
        //加载网页
        mBridgeWebView = (BridgeWebView) findViewById(R.id.web_view);
        mBridgeWebView.setDefaultHandler(new DefaultHandler());
        mBridgeWebView.loadUrl("file:///android_asset/demo.html");
        //注册交互js的callback
        registerHandler();
    }

    //getUserInfo1就是暴露给html调用的字段-handlerName
    private void registerHandler() {
        // TODO: 2018/1/27
        mBridgeWebView.registerHandler("getUserInfo1", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                //传递gson转化的实体类
//                function.onCallBack(new Gson().toJson(mUserInfo));
                //传递string
                function.onCallBack("我是app传递的string");
                Log.e(TAG, "handler = getUserInfo, 来自网页传递的数据 = " + data);
            }
        });

        // TODO: 2018/1/27
        mBridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                //传递gson转化的实体类
//                function.onCallBack(new Gson().toJson(mUserInfo));
                //传递string
                function.onCallBack("我是app传递的string");
                Log.e(TAG, "handler = getUserInfo, 来自网页传递的数据 = " + data);
            }
        });
    }

}
