package com.simei.androidjs.androidjs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private JSHelper jsHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        WebView webView = (WebView) this.findViewById(R.id.webView);
        Button button = (Button) this.findViewById(R.id.button);

        jsHelper = new JSHelper(webView);
        jsHelper.jsHelperJsMethodName(new WebAppInterface(this), "jsControl");
        jsHelper.requestUrl("file:///android_asset/index.html");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsHelper.jsHelperRunJs("javascript:alertMyName('安卓原生发过来的消息')", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        System.out.println("执行js返回值："+s);
                    }
                });
            }
        });
    }

    public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void nativeMethod(String flatName, String name) {
            System.out.println(flatName+" "+name);
            Toast.makeText(mContext, flatName+" > "+name, Toast.LENGTH_SHORT).show();
        }
    }
}
