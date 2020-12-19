package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class cart extends AppCompatActivity {

    ImageView cart_back_button;
    Button cart_view_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //ImageView
        cart_back_button = findViewById(R.id.cart_back_button);
        //Button
        cart_view_product = findViewById(R.id.cart_view_product);
        //Button on click
        cart_view_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart.this,index.class));
            }
        });

        //ImageView on click
        cart_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}