package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashudihatti.adapter.product_adapter;
import com.example.ashudihatti.info.popular_info;

import java.util.ArrayList;
import java.util.List;

public class categories_product extends AppCompatActivity {

    List<popular_info> popular_list;
    RecyclerView product_recycler;
    TextView categories_product_heading;
    ImageView categories_product_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_product);

        //RecyclerView
        product_recycler = findViewById(R.id.categories_product_recycler);

        //ImageView
        categories_product_back = findViewById(R.id.categories_product_back);

        //TextView
        categories_product_heading = findViewById(R.id.categories_product_heading);
        Intent intent = getIntent();
        String heading = intent.getStringExtra("name");
        categories_product_heading.setText(heading);

        //Back Button
        categories_product_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //popular
        popular_list = new ArrayList<>();

        popular_list.add(new popular_info("Name",R.drawable.product_image,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image1,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image2,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image3,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.sari,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image1,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image2,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image3,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.sari,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image1,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image2,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.product_image3,"Orders","1","1"));
        popular_list.add(new popular_info("Name",R.drawable.sari,"Orders","1","1"));

        product_adapter adapter = new product_adapter(getApplicationContext(), popular_list);
        product_recycler.setAdapter(adapter);

    }
}