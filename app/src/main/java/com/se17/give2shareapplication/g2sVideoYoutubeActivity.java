package com.se17.give2shareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class g2sVideoYoutubeActivity extends AppCompatActivity {

    private WebView webViewYt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2s_video_youtube);

        init();
    }

    private void init() {
        webViewYt = findViewById(R.id.webviewYt);
        webViewYt.loadUrl("https://www.youtube.com/watch?v=3n9CXlVkZMY");
    }
}
