package com.example.ashudihatti.fragments.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import java.util.HashMap;
import java.util.List;

public class CategoriesFragment extends Fragment {

    ImageView men,women,handloom;

    RecyclerView expandableListView,womenrec,handrec,rcy;
    ExpandableListAdapter expandableListAdapter;
    List<CategorySubtitle> expandableListTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);


        //ImageView Id
        men = root.findViewById(R.id.categories1);
        women = root.findViewById(R.id.categories2);
        handloom = root.findViewById(R.id.categories3);


        expandableListView = root.findViewById(R.id.expandableListView);
        womenrec = root.findViewById(R.id.womenrec);
        handrec = root.findViewById(R.id.handrec);



        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_Category(1);
                if(expandableListView.getVisibility()==View.VISIBLE)
                {
                    expandableListView.setVisibility(View.GONE);
                }
                else
                {
                    expandableListView.setVisibility(View.VISIBLE);
                }

            }
        });
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_Category(2);
                if(womenrec.getVisibility()==View.VISIBLE)
                {
                    womenrec.setVisibility(View.GONE);
                }
                else
                {
                    womenrec.setVisibility(View.VISIBLE);
                }

            }
        });
        handloom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_Category(3);
                if(handrec.getVisibility()==View.VISIBLE)
                {
                    handrec.setVisibility(View.GONE);
                }
                else
                {
                    handrec.setVisibility(View.VISIBLE);
                }

            }
        });

        return root;
    }


    String data = null;
    public void get_Category(final int val) {


        expandableListTitle = new ArrayList<>();
        if(val==1)
        {
            rcy = expandableListView;
            data = "?category=1";
        }
        if(val==2)
        {
            rcy = womenrec;
            data = "?category=2";
        }
        if(val==3)
        {
            rcy = handrec;
            data = "?category=3";
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.subcategory_api+data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);
                            Log.d("data",Constants.subcategory_api+data);
                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                CategorySubtitle home_info = new CategorySubtitle(heroObject.getString("url"),heroObject.getString("name"),heroObject.getString("slug"),heroObject.getString("category"));
                                expandableListTitle.add(home_info);


                            }
                           CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(getContext(),expandableListTitle,val);
                            rcy.setAdapter(adapter);
                            Log.d("data",""+expandableListTitle);

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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);
    }


}