package com.finalproject.class11;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstannceState){
        super.onCreate(savedInstannceState);

        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/test.html");
    }
}
