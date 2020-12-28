package com.example.ashudihatti.fragments.categories;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.R;
import com.example.ashudihatti.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomExpandableListAdapter extends RecyclerView.Adapter<CustomExpandableListAdapter.MyViewHolder> {

    ArrayList<String> cloth;
    MyViewHolder holder1;
    Context context;
    List<CategorySubtitle> apps;
    int val;

    public CustomExpandableListAdapter(Context context, List<CategorySubtitle> apps,int val) {
        this.context = context;
        this.apps = apps;
        this.val = val;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button p_name;
        RecyclerView rcy;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.listTitle);
            rcy = itemView.findViewById(R.id.rcychild);
            
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_group,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final CategorySubtitle app = apps.get(position);
        holder.p_name.setText(app.getName());
        holder.p_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1 = holder;
                if(holder.rcy.getVisibility()==View.GONE){
                    holder.rcy.setVisibility(View.VISIBLE);
                }else{
                    holder.rcy.setVisibility(View.GONE);
                }

                String s[]=apps.get(position).getId().split("/");
                get_Category(s[10]);


            }
        });


    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


    String data = null;
    public ArrayList<CategorySubtitle> get_Category(final String val1) {

        final ArrayList<CategorySubtitle> sub_category1 = new ArrayList<>();
        data = "?category="+String.valueOf(val)+"&subcategory="+val1;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.subtype_category_api+data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            cloth = new ArrayList<String>();

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray heroArray = jsonObject.getJSONArray(String.valueOf(val1));


                            for (int i = 0; i < heroArray.length(); i++) {
                                String heroObject = (String) heroArray.get(i);

                                cloth.add(heroObject);


                            }
                            Log.d("data",""+cloth);
                            CustomSubtypeAdapter customSubtypeAdapter = new CustomSubtypeAdapter(context,cloth,String.valueOf(val),val1);
                            holder1.rcy.setAdapter(customSubtypeAdapter);



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

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(stringRequest);
        return  sub_category1;
    }

}


