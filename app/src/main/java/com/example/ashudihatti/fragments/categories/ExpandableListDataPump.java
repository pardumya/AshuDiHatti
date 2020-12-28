package com.example.ashudihatti.fragments.categories;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.adapter.home_adapter;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.info.home_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    Context ctx;
    public ExpandableListDataPump(Context c)
    {
        Log.d("data",""+c);
        ctx = c;
    }
     ArrayList<CategorySubtitle> sub_category = new ArrayList<>();
    HashMap<String, List<String>> expandableListDetail;
    public HashMap<String, List<String>> getData(int val) {
        if(val==1) {
            sub_category =  get_Category(val);
            expandableListDetail = new HashMap<String, List<String>>();
            return expandableListDetail;
        }
        return null;
    }
    String data = null;
    public ArrayList<CategorySubtitle> get_Category(int val) {

        final ArrayList<CategorySubtitle> sub_category1 = new ArrayList<>();

        if(val==1)
        {
             data = "?category=1";
        }
        if(val==2)
        {
             data = "?category=2";
        }
        if(val==3)
        {
             data = "?category=3";
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.subcategory_api+data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            List<String> cricket = new ArrayList<String>();
                            JSONArray heroArray = new JSONArray(response);
                            Log.d("data",Constants.subcategory_api+data);
                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                CategorySubtitle home_info = new CategorySubtitle(heroObject.getString("url"),heroObject.getString("name"),heroObject.getString("slug"),heroObject.getString("category"));
                                sub_category.add(home_info);
                                expandableListDetail.put(sub_category.get(i).name, cricket);
                                Log.d("data",sub_category.get(i).getName());


                            }

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

        Log.d("data",""+ctx);
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);

        requestQueue.add(stringRequest);
        return  sub_category1;
    }

}
