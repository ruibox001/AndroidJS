package com.simei.androidjs.androidjs;

import android.annotation.SuppressLint;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by chuangmeihui on 16/6/22.
 */
public class JSHelper {

    private WebView webView;

    public JSHelper(WebView webView){
        this.webView = webView;
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
    }

    public void requestUrl(String url){
        if (url == null || url.length() == 0)
            return;

        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    @SuppressLint("NewApi")
    public void jsHelperRunJs(String js, ValueCallback<String> callback){
        if (js == null || js.length() == 0)
            return;

        if (webView != null) {
            webView.evaluateJavascript(js,callback);
        }
    }

    /**
     * 注册JS调用本地Android的方法回调
     * @param o 对象（如下例子）
     * @param htmlName
     * public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }
         @JavascriptInterface
         public void showToast(String toast) {
         Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
         }
         }
     */
    @SuppressLint("JavascriptInterface")
    public void jsHelperJsMethodName(Object o, String htmlName){
        if (webView != null) {
            webView.addJavascriptInterface(o, htmlName);
        }
    }
}
