package com.example.ashudihatti.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ashudihatti.R;
import com.example.ashudihatti.adapter.home_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.searchView;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageSlider slider;
    ImageView cart;
    List<home_info> new_arrival_list;
    List<home_info> best_seller_list;
    List<home_info> you_may_like_list;
    List<home_info> men_collection_list;
    List<home_info> women_collection_list;
    List<home_info> handloom_collection_list;
    ShimmerFrameLayout shimmer1,shimmer2,shimmer3,shimmer4,shimmer5;
    LinearLayout shimmer_linear_layout;
    RecyclerView new_arrival_recyclerview,best_seller__recyclerview,you_may_like_recyclerview,men_collection_recyclerView,women_collection_recyclerView,handloom_collection_recyclerView;
    TextView home_search_view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

      View root = inflater.inflate(R.layout.fragment_home, container, false);


        new_arrival_list = new ArrayList<>();
        best_seller_list = new ArrayList<>();
        you_may_like_list = new ArrayList<>();
        men_collection_list = new ArrayList<>();
        women_collection_list = new ArrayList<>();
        handloom_collection_list = new ArrayList<>();

        //Image slider Id
        slider = root.findViewById(R.id.slider);
        //ImageView Id
        cart = root.findViewById(R.id.home_cart);
        //TextView Id
        home_search_view = root.findViewById(R.id.home_search_view);
        //Linear layout Id
        shimmer_linear_layout = root.findViewById(R.id.shimmer_linear_layout);

        //recyclerview Id
        men_collection_recyclerView = root.findViewById(R.id.men_collection_recyclerView);
        women_collection_recyclerView = root.findViewById(R.id.women_collection_recyclerView);
        handloom_collection_recyclerView = root.findViewById(R.id.handloom_collection_recyclerView);

        new_arrival_recyclerview = root.findViewById(R.id.new_arrival_recyclerview);
        best_seller__recyclerview = root.findViewById(R.id.best_seller__recyclerview);
        you_may_like_recyclerview = root.findViewById(R.id.you_may_like_recyclerview);

        //shimmer animation
        shimmer1 = root.findViewById(R.id.shimmer1);
        shimmer2 = root.findViewById(R.id.shimmer2);
        shimmer3 = root.findViewById(R.id.shimmer3);
        shimmer4 = root.findViewById(R.id.shimmer4);
        shimmer5 = root.findViewById(R.id.shimmer5);

        shimmer1.startShimmer();
        shimmer2.startShimmer();
        shimmer3.startShimmer();
        shimmer4.startShimmer();
        shimmer5.startShimmer();

        //Top Image Slider
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner1,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner2,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner3,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner4,ScaleTypes.FIT));
        slider.setImageList(slideModels, ScaleTypes.FIT);

        //On Click
                //ImageView
        cart.setOnClickListener(this);
                //TextView
        home_search_view.setOnClickListener(this);

        //functions
        get_new_arrival();
        get_best_seller();
        get_you_may_like();

        get_top_men_collection();
        get_top_women_collection();
        get_top_handloom_collection();


        List<String> sr_no = new ArrayList<>();
        sr_no.add("one");
        sr_no.add("two");
        sr_no.add("three");
        sr_no.add("four");


        List<String> number = new ArrayList<>();

        for (int i=0;i<2;i++){
            String random = String.valueOf(new Random().nextInt((sr_no.size()) + 2) );
            number.add(random);
        }

        Log.d("datta", String.valueOf(number));

        return root;

    }

    public void get_top_men_collection() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;
                            List<String> demo = new ArrayList<>();

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String men_collection = heroObject.getString("category");
                                String[] men_collection_data = men_collection.split("/");

                                if(men_collection_data[10].equals("1")){
                                    String collection = heroObject.getString("url");
                                    String[] collection_data = collection.split("/");
                                    demo.add(collection_data[10]);
                                }
                            }

                            List<String> number = new ArrayList<>();

                            for (int i=0;i<demo.size();i++){
                                String random = String.valueOf(new Random().nextInt((demo.size()) + 2) );
                                number.add(random);
                            }

                            Log.d("datta", String.valueOf(number));

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String men_collection = heroObject.getString("category");
                                String[] men_collection_data = men_collection.split("/");

                                if(men_collection_data[10].equals("1")){


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
                                    men_collection_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 3){
                                    break;
                                }

                            }

                            home_adapter adapter1 = new home_adapter(getActivity(), men_collection_list);
                            men_collection_recyclerView.setAdapter(adapter1);


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }

    public void get_top_women_collection() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String men_collection = heroObject.getString("category");
                                String[] men_collection_data = men_collection.split("/");

                                if(men_collection_data[10].equals("2")){

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
                                    women_collection_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 3){
                                    break;
                                }

                            }

                            home_adapter adapter1 = new home_adapter(getActivity(), women_collection_list);
                            women_collection_recyclerView.setAdapter(adapter1);


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }

    public void get_top_handloom_collection() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String men_collection = heroObject.getString("category");
                                String[] men_collection_data = men_collection.split("/");

                                if(men_collection_data[10].equals("3")){

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
                                    handloom_collection_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 3){
                                    break;
                                }

                            }

                            home_adapter adapter1 = new home_adapter(getActivity(), handloom_collection_list);
                            handloom_collection_recyclerView.setAdapter(adapter1);


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }

    public void get_new_arrival(){


        int min = 20;
        int max = 80;
        List<String> number = new ArrayList<>();

        for (int i=0;i<3;i++){
            String random = String.valueOf(new Random().nextInt((max - min) + 2) + min);
            number.add(random);
        }


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                if(heroObject.getString("type").equals("NA")){
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
                                    new_arrival_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 6){
                                    break;
                                }

                            }

                            home_adapter adapter1 = new home_adapter(getActivity(), new_arrival_list);
                            new_arrival_recyclerview.setAdapter(adapter1);


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }

    public void get_best_seller(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                if(heroObject.getString("type").equals("BS")){
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
                                    best_seller_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 6){
                                    break;
                                }

                            }

                            home_adapter adapter2 = new home_adapter(getActivity(), best_seller_list);
                            best_seller__recyclerview.setAdapter(adapter2);


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }

    public void get_you_may_like(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            int Count=0;

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                if(heroObject.getString("type").equals("null")){
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
                                    you_may_like_list.add(home_info);
                                    Count++;
                                }

                                if(Count == 6){
                                    break;
                                }

                            }


                            home_adapter adapter3 = new home_adapter(getActivity(), you_may_like_list);
                            you_may_like_recyclerview.setAdapter(adapter3);

                            shimmer_linear_layout.setVisibility(View.GONE);
                            shimmer1.stopShimmer();
                            shimmer2.stopShimmer();
                            shimmer3.stopShimmer();
                            shimmer4.stopShimmer();
                            shimmer5.stopShimmer();


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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.home_search_view){
            startActivity(new Intent(getActivity(), searchView.class));
        }else if(view.getId() == R.id.home_cart){
            startActivity(new Intent(getActivity(), com.example.ashudihatti.cart.class));
        }
    }
}