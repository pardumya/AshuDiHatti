package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.adapter.home_adapter;
import com.example.ashudihatti.adapter.product_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.info.product_info_data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class product extends AppCompatActivity implements View.OnClickListener{

    TextView product_heading;
    ImageView product_back_button;
    RecyclerView product_recycler_View;
    List<String> products = new ArrayList<>();
    List<product_info_data> product_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product_recycler_View = findViewById(R.id.product_recycler_View);

        product_back_button = findViewById(R.id.product_back_button);

        product_heading = findViewById(R.id.product_heading);
        //Get-Intent
        Intent intent = getIntent();

        //On Click
        product_back_button.setOnClickListener(this);;

        get_date_product(intent.getStringExtra("date"));
    }

    public void get_date_product(final String date){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.fb_live_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                if(date.equals(heroObject.getString("date"))){

                                    String id = heroObject.getString("prodsr_no");
                                    String[] data = id.split("/");

                                    products.add(data[10]);
                                }
                            }

                            get_product();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(product.this);

        requestQueue.add(stringRequest);
    }

    private void get_product() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String product_id = heroObject.getString("url");
                                String[] product_id_data = product_id.split("/");

                                for(int j=0;j<products.size();j++){
                                    if(products.get(j).equals(product_id_data[10])){

                                        List<String> color = new ArrayList<>();
                                        List<String> images = new ArrayList<>();

                                        color.add(heroObject.getString("color"));

                                        images.add(heroObject.getString("image1"));
                                        images.add(heroObject.getString("image2"));
                                        images.add(heroObject.getString("image3"));
                                        images.add(heroObject.getString("image4"));

                                        String product_url_id = heroObject.getString("url");
                                        String[] product_url_id_data = product_url_id.split("/");

                                        String product_category = heroObject.getString("category");
                                        String[] product_category_data = product_category.split("/");

                                        String product_subcategory = heroObject.getString("subcategory");
                                        String[] product_subcategory_data = product_subcategory.split("/");

                                        product_info_data home_info = new product_info_data(product_url_id_data[10],heroObject.getString("name"),heroObject.getString("sr_no"),product_category_data[10],product_subcategory_data[10],heroObject.getString("subcat_type"),heroObject.getString("lable"),color,heroObject.getString("price"),heroObject.getString("discount_price"),heroObject.getString("type"),heroObject.getString("description"),heroObject.getString("specification"),heroObject.getString("rating"),heroObject.getString("stock"),images);
                                        product_list.add(home_info);
                                        break;
                                    }
                                }
                            }

                            product_adapter adapter = new product_adapter(product.this, product_list);
                            product_recycler_View.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(product.this);

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        //back button
        if(view.getId() == R.id.product_back_button){
            finish();
        }
    }
}