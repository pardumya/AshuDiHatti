package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.adapter.cart_adapter;
import com.example.ashudihatti.adapter.home_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.home_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class cart extends AppCompatActivity implements View.OnClickListener {

    ImageView cart_back_button;
    Button cart_view_product,place_your_order;
    RecyclerView cart_recyclerView;
    ConstraintLayout empty_cart;
    LinearLayout cart_not_empty;
    List<String> product_id = new ArrayList<>();
    List<String> product_quantity = new ArrayList<>();
    List<String> product_color = new ArrayList<>();
    String cart;
    int pi=0;
    int pq=1;
    int pc=2;

    List<home_info> cart_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //ImageView
        cart_back_button = findViewById(R.id.cart_back_button);
        //Button
        cart_view_product = findViewById(R.id.cart_view_product);
        place_your_order = findViewById(R.id.place_your_order);
        //RecyclerView
        cart_recyclerView = findViewById(R.id.cart_recyclerView);
        //ConstraintLayout
        empty_cart = findViewById(R.id.empty_cart);
        //LinearLayout
        cart_not_empty = findViewById(R.id.cart_not_empty);

        //On Click
        cart_view_product.setOnClickListener(this);
        cart_back_button.setOnClickListener(this);

        checking_cart_product();

    }

    public void checking_cart_product(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.cart_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String id = heroObject.getString("user");
                                String[] data = id.split("/");

                                if(data[10].equals("1")){
                                    cart = heroObject.getString("cart");
                                    break;
                                }else{
                                    empty_cart.setVisibility(View.VISIBLE);
                                    cart_not_empty.setVisibility(View.GONE);
                                    place_your_order.setVisibility(View.GONE);
                                }

                            }

                            String[] cart_data = cart.split(",");
                            for(int i=0;i<cart_data.length;i++){
                                if(i == pi){
                                    product_id.add(cart_data[pi]);
                                    pi = i+3;
                                }else if(i == pq){
                                    product_quantity.add(cart_data[pq]);
                                    pq = i+3;
                                }else if(i == pc){
                                    product_color.add(cart_data[pc]);
                                    pc = i+3;
                                }
                            }

                            get_cart_product();

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

        RequestQueue requestQueue = Volley.newRequestQueue(cart.this);

        requestQueue.add(stringRequest);
    }

    public void get_cart_product(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                for(int j=0;j<product_id.size();j++){
                                    String id = heroObject.getString("url");
                                    String[] data = id.split("/");
                                    if(product_id.get(j).equals(data[10])){

                                        List<String> color = new ArrayList<>();
                                        List<String> images = new ArrayList<>();

                                        color.add(heroObject.getString("color"));

                                        images.add(heroObject.getString("image1"));
                                        images.add(heroObject.getString("image2"));
                                        images.add(heroObject.getString("image3"));
                                        images.add(heroObject.getString("image4"));

                                        String product_id = heroObject.getString("url");
                                        String[] product_id_data = product_id.split("/");

                                        String product_category = heroObject.getString("category");
                                        String[] product_category_data = product_category.split("/");

                                        String product_subcategory = heroObject.getString("subcategory");
                                        String[] product_subcategory_data = product_subcategory.split("/");


                                        home_info home_info = new home_info(product_id_data[10],heroObject.getString("name"),heroObject.getString("sr_no"),product_category_data[10],product_subcategory_data[10],heroObject.getString("subcat_type"),heroObject.getString("lable"),color,heroObject.getString("price"),heroObject.getString("discount_price"),heroObject.getString("type"),heroObject.getString("description"),heroObject.getString("specification"),heroObject.getString("rating"),heroObject.getString("stock"),images);
                                        cart_list.add(home_info);
                                        break;
                                    }
                                }

                            }

                            cart_adapter adapter = new cart_adapter(cart.this, cart_list,product_quantity);
                            cart_recyclerView.setAdapter(adapter);


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

        RequestQueue requestQueue = Volley.newRequestQueue(cart.this);

        requestQueue.add(stringRequest);
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