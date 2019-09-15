package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class webview extends AppCompatActivity {
Intent intent;
String url;
WebView webview;

WebChromeClient webChromeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        intent=getIntent();
        url=intent.getStringExtra("link");

        webview=findViewById(R.id.webview);
        Toast.makeText(this,url,Toast.LENGTH_LONG).show();
     webChromeClient=new WebChromeClient();

        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);

 webview.setWebChromeClient(webChromeClient);
    }

}
