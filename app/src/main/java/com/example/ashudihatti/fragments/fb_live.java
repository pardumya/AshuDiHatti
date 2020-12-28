package com.example.ashudihatti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.R;
import com.example.ashudihatti.adapter.fb_live_adapter;
import com.example.ashudihatti.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class fb_live extends Fragment {

    RecyclerView fb_live_recycler_view;

    public fb_live() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_fb_live, container, false);

        fb_live_recycler_view = layout.findViewById(R.id.fb_live_recycler_view);

        get_fb_live_product_date();

        return layout;
    }

    public void get_fb_live_product_date() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.fb_live_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray Product_Array = new JSONArray(response);

                            ArrayList<String> numbersList = new ArrayList<>();

                            for (int i = 0; i < Product_Array.length(); i++) {
                                JSONObject Product_Object = Product_Array.getJSONObject(i);

                                numbersList.add(Product_Object.getString("date"));
                            }

                            LinkedHashSet<String> hashSet = new LinkedHashSet<>(numbersList);
                            ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);


                            fb_live_adapter adapter = new fb_live_adapter(getActivity(), listWithoutDuplicates);
                            fb_live_recycler_view.setAdapter(adapter);


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

    public void get_fb_live_product() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.product_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            int Count =0;
                            ArrayList<String> numbersList = new ArrayList<>();

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                numbersList.add(heroObject.getString("date"));
                            }

                            LinkedHashSet<String> hashSet = new LinkedHashSet<>(numbersList);

                            ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);

                            fb_live_adapter adapter = new fb_live_adapter(getActivity(), listWithoutDuplicates);
                            fb_live_recycler_view.setAdapter(adapter);


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

}