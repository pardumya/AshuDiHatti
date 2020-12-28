package com.example.ashudihatti.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashudihatti.R;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.product_info;

import java.util.ArrayList;
import java.util.List;

public class search_view_adapter extends RecyclerView.Adapter<search_view_adapter.MyViewHolder> {

    Context context;
    List<home_info> apps;

    public search_view_adapter(Context context, List<home_info> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.search_view_product_search);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.search_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final home_info app = apps.get(position);

        holder.p_name.setText(app.getProduct_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);

                Intent intent = new Intent(context, product_info.class);
                intent.putStringArrayListExtra("Images", (ArrayList<String>) app.getProduct_Image());
                intent.putStringArrayListExtra("Color", (ArrayList<String>) app.getProduct_Color());
                intent.putExtra("product_id",app.getProduct_id());
                intent.putExtra("name",app.getProduct_name());
                intent.putExtra("price",app.getProduct_Price());
                intent.putExtra("discount",app.getProduct_Discount_Price());
                intent.putExtra("description",app.getProduct_Description());
                intent.putExtra("specification",app.getProduct_Specification());
                intent.putExtra("rating",app.getProduct_Rating());
                intent.putExtra("label",app.getProduct_Label());

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
