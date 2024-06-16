package com.app.eventosirest1;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);



        webView = findViewById(R.id.webview);
        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String urlToRegister = getIntent().getStringExtra("URL"); // Get the passed URL
        webView.loadUrl(urlToRegister);
        webView.clearCache(true);


    }
}
