package com.example.ashudihatti;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashudihatti.info.home_info;


import java.util.ArrayList;
import java.util.List;

public class searchView extends AppCompatActivity {

    SearchView searchView;
    ImageButton search_view_back;
    RecyclerView search_view_recyclerview;
    ArrayList<String> name;
    ArrayList<String> image;
    ArrayList<String> price;
    ArrayList<String> discount;
    List<String> Images = new ArrayList<>();
    List<home_info> heroList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        //ImageButton
        search_view_back = findViewById(R.id.search_view_back);

        //SearchView
        searchView = findViewById(R.id.search_view_search);

        //RecyclerView
        search_view_recyclerview = findViewById(R.id.search_view_recyclerview);
        search_view_recyclerview.setHasFixedSize(true);
        search_view_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        search_view_recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //ArrayList
        name = new ArrayList<>();
        image = new ArrayList<>();
        price = new ArrayList<>();
        discount = new ArrayList<>();

        searchView.requestFocus();
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.RESULT_HIDDEN);
        if(!searchView.isFocused()) {
            imm.hideSoftInputFromWindow(searchView.getWindowToken(),0);
        }

        //ImageButton on click
        search_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);

                finish();
            }
        });

        //Search on click listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.equals("")){

                }else{
                    heroList.clear();
                    search_view_recyclerview.removeAllViews();
                }
                return false;
            }
        });
    }

}