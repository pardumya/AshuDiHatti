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


public class cart extends AppCompatActivity implements View.OnClickListener {

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


        //On Click
        cart_view_product.setOnClickListener(this);
        cart_back_button.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cart_back_button){
            finish();
        }else if(view.getId() == R.id.cart_view_product){
            startActivity(new Intent(cart.this,index.class));
        }
    }
}