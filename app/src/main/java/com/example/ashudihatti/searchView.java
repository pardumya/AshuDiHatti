package com.example.ashudihatti;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.adapter.search_view_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.home_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class searchView extends AppCompatActivity implements View.OnClickListener {

    SearchView searchView;
    ImageButton search_view_back;
    RecyclerView search_view_recyclerview;
    ArrayList<String> name;
    ArrayList<String> image;
    ArrayList<String> price;
    ArrayList<String> discount;
    List<home_info> heroList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        //ImageButton
        search_view_back = findViewById(R.id.search_view_back);
        //SearchView
        searchView = findViewById(R.id.search_view_search);
        //RecyclerView
        search_view_recyclerview = findViewById(R.id.search_view_recyclerview);
        search_view_recyclerview.setHasFixedSize(true);
        search_view_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        search_view_recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //ArrayList
        name = new ArrayList<>();
        image = new ArrayList<>();
        price = new ArrayList<>();
        discount = new ArrayList<>();

        searchView.requestFocus();
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.RESULT_HIDDEN);
        if(!searchView.isFocused()) {
            imm.hideSoftInputFromWindow(searchView.getWindowToken(),0);
        }

        //On Click
        search_view_back.setOnClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.equals("")){
                    setAdapter(s);
                }else{
                    heroList.clear();
                    search_view_recyclerview.removeAllViews();
                }
                return false;
            }
        });
    }

    private void setAdapter(final String search_text) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            heroList.clear();
                            search_view_recyclerview.removeAllViews();
                            int counter=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                if(heroObject.getString("subcat_type").toLowerCase().contains(search_text.toLowerCase()) || heroObject.getString("name").toLowerCase().contains(search_text.toLowerCase()) || heroObject.getString("subcategory").contains(search_text)){

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
                                    heroList.add(home_info);
                                    counter++;
                                }
                                if(counter==10){
                                    break;
                                }
                            }

                            search_view_adapter search_view_adapter = new search_view_adapter(getApplicationContext(), heroList);
                            search_view_recyclerview.setAdapter(search_view_adapter);


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

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        //back button
        if(view.getId() == R.id.search_view_back){
            finish();
        }
    }
}