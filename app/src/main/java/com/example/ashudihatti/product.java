package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class product extends AppCompatActivity {

    TextView product_heading;
    ImageView product_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //Get-Intent
        Intent intent = getIntent();
        //ImageView
        product_back_button = findViewById(R.id.product_back_button);
        product_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //TextView Id
        product_heading = findViewById(R.id.product_heading);
        product_heading.setText(intent.getStringExtra("heading"));

    }

}