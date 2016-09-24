package com.example.dllo.thirtysixkr.web;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.thirtysixkr.BaseActivity;
import com.example.dllo.thirtysixkr.R;
import com.example.dllo.thirtysixkr.tools.url.Kr36Url;
import com.example.dllo.thirtysixkr.tools.web_request.SendGetRequest;

public class WebDetailActivity extends BaseActivity{

    private WebView wb;

    @Override
    protected int setLayout() {
        return R.layout.activity_web_detail;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();

        String urlFeedId = intent.getStringExtra("web");

        String url = Kr36Url.detail_web(urlFeedId);

        wb = (WebView) findViewById(R.id.wv_detail);
        wb.setWebViewClient(new MyWebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);
        SendGetRequest.sendGetRequest(url, WebDetailBean.class, new SendGetRequest.OnResponseListener<WebDetailBean>() {
            @Override
            public void onResponse(WebDetailBean response) {
                Log.d("WebDetailActivity", response.getData().getContent());
                wb.loadData(response.getData().getContent(),"text/html; charset=UTF-8",null);

            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    protected void initData() {

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
            return false;
        }


    }
}
