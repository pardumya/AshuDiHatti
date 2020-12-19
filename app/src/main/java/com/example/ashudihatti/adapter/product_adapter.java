package com.example.ashudihatti.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ashudihatti.R;
import com.example.ashudihatti.info.popular_info;
import com.example.ashudihatti.product_info;

import java.util.List;

public class product_adapter extends RecyclerView.Adapter<product_adapter.MyViewHolder> {

    Context context;
    List<popular_info> apps;

    public product_adapter(Context context, List<popular_info> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_name;
        ImageView p_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.popular_product_name);
            p_image = itemView.findViewById(R.id.popular_product_image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.popular_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final popular_info app = apps.get(position);

        holder.p_name.setText(app.getProduct_name());
        holder.p_image.setImageResource(app.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, product_info.class);
                intent.putExtra("Image",app.getImage());
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
