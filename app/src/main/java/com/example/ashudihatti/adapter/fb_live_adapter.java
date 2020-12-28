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

import com.example.ashudihatti.R;
import com.example.ashudihatti.product;

import java.util.ArrayList;
import java.util.List;

public class fb_live_adapter extends RecyclerView.Adapter<fb_live_adapter.MyViewHolder> {

    Context context;
    List<String> apps;

    public fb_live_adapter(Context context, ArrayList<String> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_name;
        ImageView p_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.product_name);
            p_image = itemView.findViewById(R.id.product_image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.product_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final String app = apps.get(position);

        holder.p_name.setText(app);
//        Glide.with(context).load(app.getProduct_Image().get(0)).into(holder.p_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, product.class);
                intent.putExtra("date",app);
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
