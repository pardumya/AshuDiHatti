package com.example.ashudihatti.fragments.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.R;
import com.example.ashudihatti.adapter.product_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.product_info_data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    RecyclerView order_recycler_view;

    List<product_info_data> order_list = new ArrayList<>();
    List<String> product_id = new ArrayList<>();
    List<String> name = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);

        order_recycler_view = root.findViewById(R.id.order_recycler_view);

        get_Order();
        return root;
    }


    private void get_Order() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.order_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String customer = heroObject.getString("customer");
                                String[] data = customer.split("/");

                                if(data[10].equals("4")){

                                    String product_url = heroObject.getString("product");
                                    String[] product_data = product_url.split("/");

                                    product_id.add(String.valueOf(product_data[10]));
                                }
                            }

                            Log.d("datta", String.valueOf(product_id));
                            get_Product();

                        } catch (JSONException e) {
                            Log.e("data",e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
    }

    private void get_Product() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                String customer = heroObject.getString("url");
                                String[] data = customer.split("/");
                                for(String list:product_id){
                                    if(data[10].equals(list)){

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

                                        product_info_data home_info = new product_info_data(product_id_data[10],heroObject.getString("name"),heroObject.getString("sr_no"),product_category_data[10],product_subcategory_data[10],heroObject.getString("subcat_type"),heroObject.getString("lable"),color,heroObject.getString("price"),heroObject.getString("discount_price"),heroObject.getString("type"),heroObject.getString("description"),heroObject.getString("specification"),heroObject.getString("rating"),heroObject.getString("stock"),images);
                                        order_list.add(home_info);

                                        name.add(heroObject.getString("name"));
                                    }
                                }
                            }

                            product_adapter adapter = new product_adapter(getActivity(), order_list);
                            order_recycler_view.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.e("data",e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
    }
}