package com.example.ashudihatti.fragments.categories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashudihatti.R;
import com.example.ashudihatti.product;

import java.util.ArrayList;

public class CustomSubtypeAdapter extends RecyclerView.Adapter<CustomSubtypeAdapter.MyViewHolder> {

    ArrayList<String> cloth;
    Context con;
    String cat,subcat;
    public CustomSubtypeAdapter(Context con,ArrayList<String> cloth,String cat,String subcat) {
        this.cloth = cloth;
        this.con = con;
        this.cat=cat;
        this.subcat=subcat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Log.d("data",""+cloth.size());
        holder.text.setText(cloth.get(position));
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, product.class);
                intent.putExtra("ProductData",new String[]{cat,subcat, (String) holder.text.getText()});
                con.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cloth.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.expandedListItem);
        }
    }
}
