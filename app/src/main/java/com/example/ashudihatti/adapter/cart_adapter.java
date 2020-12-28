package com.example.ashudihatti.adapter;


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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.ashudihatti.R;
import com.example.ashudihatti.constants.Constants;
import com.example.ashudihatti.index;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.login;
import com.example.ashudihatti.product_info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cart_adapter extends RecyclerView.Adapter<cart_adapter.MyViewHolder> {

    Context context;
    List<home_info> apps;
    List<String> product_quantity;
    ArrayList<Integer> list = new ArrayList<>();
    int item = 1;
    String message;

    public cart_adapter(Context context, List<home_info> apps, List<String> product_quantity) {
        this.context = context;
        this.apps = apps;
        this.product_quantity = product_quantity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_name,cart_item_show,cart_product_old_price,cart_product_new_price,mrp;
        ImageView p_image,cart_item_sub,cart_item_add;
        Button cart_dustbin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.cart_product_name);
            cart_item_show = itemView.findViewById(R.id.cart_item_show);
            cart_product_old_price=itemView.findViewById(R.id.cart_product_old_price);
            cart_product_new_price=itemView.findViewById(R.id.cart_product_new_price);
            mrp = itemView.findViewById(R.id.mrp);

            p_image = itemView.findViewById(R.id.cart_product_image);
            cart_item_sub = itemView.findViewById(R.id.cart_item_sub);
            cart_item_add = itemView.findViewById(R.id.cart_item_add);

            cart_dustbin = itemView.findViewById(R.id.cart_dustbin);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final home_info app = apps.get(position);

        holder.p_name.setText(app.getProduct_name());
        Glide.with(context).load(app.getProduct_Image().get(0)).into(holder.p_image);

        if(!app.getProduct_Discount_Price().equals("0")){
            //holder.sale_tag.setVisibility(View.VISIBLE);
            holder.cart_product_old_price.setText(app.getProduct_Price());
            holder.cart_product_old_price.setVisibility(View.VISIBLE);
            holder.cart_product_new_price.setText(app.getProduct_Discount_Price());
        }else{
            holder.cart_product_new_price.setText(app.getProduct_Price());
            holder.cart_product_old_price.setVisibility(View.GONE);
            //holder.sale_tag.setVisibility(View.GONE);
            holder.mrp.setVisibility(View.GONE);
        }

        holder.cart_item_show.setText(product_quantity.get(position));
        list.add(Integer.parseInt(product_quantity.get(position)));


        holder.cart_item_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position) > 1){
                    item = list.get(position)-1;
                    list.set(position,item);
                    holder.cart_item_show.setText(String.valueOf(list.get(position)));
                }else{
                    holder.cart_item_sub.setImageResource(R.drawable.sub_main);
                }
            }
        });

        holder.cart_item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position) >= 6){
                    Toast.makeText(context, " you reach product max order quantity.", Toast.LENGTH_SHORT).show();
                }else{
                    item = list.get(position)+1;
                    list.set(position,item);
                    holder.cart_item_show.setText(String.valueOf(list.get(position)));
                }
            }
        });

        holder.cart_dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                final Map<String, String> postParam= new HashMap<>();
//                postParam.put("email", email);
//
//                final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.DELETE,
//                        Constants.login_api, new JSONObject(postParam),
//                        new Response.Listener<JSONObject>() {
//
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    message = "d";
//
//
//
//                                } catch (JSONException e) {
//                                    Log.d("datta","s"+e.getMessage());
//                                    message = "s"+e.getMessage();
//                                }
//
//                            }
//                        }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        message = "Try-Again Later";
//                        Log.d("datta","s"+error.getMessage());
//                    }
//                }) {
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        HashMap<String, String> headers = new HashMap<String, String>();
//                        headers.put("Content-Type", "application/json; charset=utf-8");
//                        return headers;
//                    }
//                };

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, product_info.class);
                intent.putStringArrayListExtra("Images", (ArrayList<String>) app.getProduct_Image());
                intent.putExtra("product_id",app.getProduct_id());
                intent.putExtra("name",app.getProduct_name());
                intent.putExtra("price",app.getProduct_Price());
                intent.putExtra("discount",app.getProduct_Discount_Price());
                intent.putExtra("description",app.getProduct_Description());
                intent.putExtra("specification",app.getProduct_Specification());
                intent.putExtra("rating",app.getProduct_Rating());
                intent.putExtra("label",app.getProduct_Label());
                intent.putStringArrayListExtra("Color", (ArrayList<String>) app.getProduct_Color());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}
