package com.example.ashudihatti.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.ashudihatti.R;

public class settings extends Fragment implements View.OnClickListener{

    ConstraintLayout faqs,about_us,manage_address;

    public settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);

        //Constraint Layout
        faqs = layout.findViewById(R.id.faqs);
        about_us = layout.findViewById(R.id.about_us);
        manage_address = layout.findViewById(R.id.manage_address);

        //On Click
        faqs.setOnClickListener(this);
        about_us.setOnClickListener(this);
        manage_address.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View view) {
        //calling faqs activity
        if(view.getId() == R.id.faqs){
            startActivity(new Intent(getActivity(), faqs.class));
        }
        //calling about_us activity
        else if(view.getId() == R.id.about_us){
            startActivity(new Intent(getActivity(), about_us.class));
        }
        //calling manage_address activity
        else if(view.getId() == R.id.manage_address){
            startActivity(new Intent(getActivity(), manage_address.class));
        }
    }

}