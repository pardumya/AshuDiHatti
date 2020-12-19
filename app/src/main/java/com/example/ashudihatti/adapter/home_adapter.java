package com.example.ashudihatti.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ashudihatti.R;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.info.popular_info;
import com.example.ashudihatti.product_info;

import java.util.ArrayList;
import java.util.List;

public class home_adapter extends RecyclerView.Adapter<home_adapter.MyViewHolder> {

    Context context;
    List<home_info> apps;

    public home_adapter(Context context, List<home_info> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_name;
        ImageView p_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.home_product_name);
            p_image = itemView.findViewById(R.id.home_product_image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.home_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final home_info app = apps.get(position);

        holder.p_name.setText(app.getProduct_name());
        Glide.with(context).load(app.getProduct_Image().get(0)).into(holder.p_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, product_info.class);
                intent.putStringArrayListExtra("Images", (ArrayList<String>) app.getProduct_Image());
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
