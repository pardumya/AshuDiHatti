package com.example.ashudihatti.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ashudihatti.R;
import com.example.ashudihatti.index;


public class about_us extends AppCompatActivity {

    //ImageView
    ImageView about_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //ImageView
        about_back = findViewById(R.id.about_back);

        //ImageView on click
        about_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(about_us.this, index.class));
            }
        });

    }
}