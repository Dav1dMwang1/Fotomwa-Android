package com.example.dijonkariz.fotomwa.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dijonkariz.fotomwa.R;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.shape2);
//        //Load animation
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        //start animation
        imageView.startAnimation(anim);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent towelcomeslider = new Intent(MainActivity.this, WelcomeSlider.class);
                MainActivity.this.startActivity(towelcomeslider);
                MainActivity.this.finish();
            }
        }, 2500);
    }
}
