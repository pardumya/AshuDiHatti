package com.example.ashudihatti;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    Animation loading_image;
    ImageView loading_logo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        loading_logo = findViewById(R.id.loading_logo);

        loading_image = AnimationUtils.loadAnimation(this,R.anim.loading_image_animation);

        loading_logo.setAnimation(loading_image);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Loading.this, index.class);
                Pair[] Pairs = new Pair[1];
                Pairs[0] = new Pair<View,String>(loading_logo,"loading_logo_image");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Loading.this,Pairs);
                startActivity(i,options.toBundle());
            }
        }, 1800);
    }
}

