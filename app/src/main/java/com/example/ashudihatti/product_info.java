package com.example.ashudihatti;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ashudihatti.adapter.product_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.product_info_data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class product_info extends AppCompatActivity implements View.OnClickListener {

    ImageView product_info_back_button,product_info_item_sub,product_info_item_add;
    TextView product_info_item_show,product_info_name,product_info_price,product_info_discount,product_info_description,product_info_specification,product_rating;
    Button product_info_add,product_info_view;
    ProgressBar product_info_progressbar;
    List<String> images = new ArrayList<>();
    List<String> color = new ArrayList<>();
    String product_name,product_Label,product_Price,product_Discount_Price,product_Description,product_Specification,product_rating_value,product_id,stringRequest_type,cart_products,stringCartId,api;
    ImageSlider product_info_image_slider;
    RatingBar RatingBar;
    RequestQueue queue;

    int item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        queue = Volley.newRequestQueue(this);

        //Intent Get Data
        Intent intent = getIntent();
        images =  intent.getStringArrayListExtra("Images");
        color =  intent.getStringArrayListExtra("Color");
        product_id = intent.getStringExtra("product_id");
        product_name = intent.getStringExtra("name");
        product_Price = intent.getStringExtra("price");
        product_Discount_Price = intent.getStringExtra("discount");
        product_Description = intent.getStringExtra("description");
        product_Specification = intent.getStringExtra("specification");
        product_rating_value = intent.getStringExtra("rating");
        product_Label = intent.getStringExtra("label");
        //TextView Id
        product_info_price = findViewById(R.id.product_info_price);
        product_info_discount = findViewById(R.id.product_info_discount);
        product_info_name = findViewById(R.id.product_info_name);
        product_info_description = findViewById(R.id.product_info_description);
        product_info_item_show = findViewById(R.id.product_info_item_show);
        product_info_specification = findViewById(R.id.product_info_specification);
        product_rating = findViewById(R.id.product_rating);
        item = Integer.parseInt(product_info_item_show.getText().toString());
        //ImageSlider Id
        product_info_image_slider = findViewById(R.id.product_info_image_slider);
        //Rating Bar
        RatingBar = findViewById(R.id.RatingBar);
        //ImageView Id
        product_info_back_button = findViewById(R.id.product_info_back_button);
        product_info_item_sub = findViewById(R.id.product_info_item_sub);
        product_info_item_add = findViewById(R.id.product_info_item_add);
        //Progress Bar Id
        product_info_progressbar = findViewById(R.id.product_info_progressbar);
        //Button Id
        product_info_add = findViewById(R.id.product_info_add);
        product_info_view = findViewById(R.id.product_info_view);


        //setting data
        setting_data();

        //On Click
        product_info_back_button.setOnClickListener(this);
        product_info_item_sub.setOnClickListener(this);
        product_info_item_add.setOnClickListener(this);
        product_info_add.setOnClickListener(this);
        product_info_view.setOnClickListener(this);
    }

    public void setting_data(){
        //Image
        List<SlideModel> slideModels = new ArrayList<>();
        for(int i =0;i<images.size();i++){
            if(!images.get(i).equals("null")) {
                slideModels.add(new SlideModel(images.get(i), ScaleTypes.FIT));
            }
        }
        product_info_image_slider.setImageList(slideModels, ScaleTypes.FIT);
        //Product Name
        product_info_name.setText(product_name);
        //Product Price
        if(!product_Discount_Price.equals("0")){
            product_info_discount.setText(product_Price);
            product_info_price.setPaintFlags(product_info_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            product_info_price.setText(product_Discount_Price);
            product_info_discount.setVisibility(View.VISIBLE);
        }else{
            product_info_price.setText(product_Price);
            product_info_discount.setVisibility(View.GONE);
        }
        //Product Description
        product_info_description.setText(product_Description);
        //Product Specification
        product_info_specification.setText(product_Specification);
        //Product Rating
        product_rating.setText(product_rating_value);

    }

    public void get_product_info(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.cart_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String customer = heroObject.getString("user");
                                String[] data = customer.split("/");

                                if(data[10].equals("1")){
                                    String cart_id = heroObject.getString("url");
                                    String[] cart_id_data = cart_id.split("/");
                                    stringCartId= cart_id_data[10];
                                    cart_products = heroObject.getString("cart");
                                    break;
                                }else{
                                    stringCartId="";
                                }
                            }

                            post_product_info();

                        } catch (JSONException e) {
                            Log.e("data",e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(product_info.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(product_info.this);

        requestQueue.add(stringRequest);
    }

    public void post_product_info(){

        String quantity = product_info_item_show.getText().toString();
        String product_data = product_id+","+quantity+","+"red";

        String cart;

        if(stringCartId.equals("")){
            api = Constants.cart_api;
            cart = product_data;
            stringRequest_type = String.valueOf(Request.Method.POST);
        }else{
            stringRequest_type = String.valueOf(Request.Method.PUT);
            api = Constants.cart_api+stringCartId+"/";
            cart = cart_products+","+product_data;
        }

        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("cart", cart);
        postParam.put("user", "http://ashudihatti.in/rohar/tech/ashu/di/hatti/api/customer/1/");


        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Integer.parseInt(stringRequest_type),
                api, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if(!response.equals("null")){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        queue.add(jsonObjReq);
    }

    @Override
    public void onClick(View view) {
        //back button
        if(R.id.product_info_back_button == view.getId()){
            finish();
        }
        //product quantity decrease
        else if(view.getId() == R.id.product_info_item_sub){
            if(item != 1){
                item = item-1;
                product_info_item_show.setText(String.valueOf(item));
            }
        }
        //product quantity increase
        else if(view.getId() == R.id.product_info_item_add){
            item = item+1;
            product_info_item_show.setText(String.valueOf(item));
        }
        //product add to cart
        else if(view.getId() == R.id.product_info_add){
            product_info_progressbar.setVisibility(View.VISIBLE);
            product_info_add.setVisibility(View.GONE);
            get_product_info();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    product_info_progressbar.setVisibility(View.GONE);
                    product_info_view.setVisibility(View.VISIBLE);
                }
            }, 1000);
        }
        //product view to cart
        else if(view.getId() == R.id.product_info_view){
            startActivity(new Intent(product_info.this,cart.class));
        }
    }
}