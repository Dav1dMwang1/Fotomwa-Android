package com.example.dijonkariz.fotomwa.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dijonkariz.fotomwa.R;

public class TermsAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        Toolbar toolbar = findViewById(R.id.terms_toolbar);
        toolbar.setTitle(R.string.terms_and_conditions);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fmwa_red, getTheme()));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        WebView myWebView = findViewById(R.id.terms_webview);
        myWebView.loadUrl("https://www.websitepolicies.com/policies/view/7IPttZ2P");
//        myWebView.loadUrl("https://fotomwa.herokuapp.com/");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
