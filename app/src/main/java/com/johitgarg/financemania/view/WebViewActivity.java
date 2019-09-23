package com.johitgarg.financemania.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.johitgarg.financemania.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

        WebView webView = findViewById(R.id.news_web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
