package com.example.ashudihatti.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.ashudihatti.ForgetPasswordDialog;
import com.example.ashudihatti.R;
import com.example.ashudihatti.constants.Constants;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class settings extends Fragment implements View.OnClickListener{

    ConstraintLayout faqs,about_us,manage_address;
    LinearLayout top_edit;
    Button profile_update,change_password;
    TextInputLayout settings_edit_name,settings_edit_number;
    EditText settings_edit_email;
    SharedPreferences preferences;
    String user_name,user_email,user_phone;

    public settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);

        //SharedPreferences
        preferences = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        user_name = preferences.getString("name",null);
        user_email = preferences.getString("email",null);
        user_phone = preferences.getString("phone",null);
        //TextInputLayout
        settings_edit_name = layout.findViewById(R.id.settings_edit_name);
        settings_edit_name.getEditText().setText(user_name);
        settings_edit_number = layout.findViewById(R.id.settings_edit_number);
        settings_edit_number.getEditText().setText(user_phone);
        //EditText
        settings_edit_email = layout.findViewById(R.id.settings_edit_email);
        settings_edit_email.setText(user_email);
        //Constraint Layout
        faqs = layout.findViewById(R.id.faqs);
        about_us = layout.findViewById(R.id.about_us);
        manage_address = layout.findViewById(R.id.manage_address);
        //Linear Layout
        top_edit = layout.findViewById(R.id.top_edit);
        //Button
        change_password = layout.findViewById(R.id.change_password);
        profile_update = layout.findViewById(R.id.profile_update);

        if(user_name != null){
            top_edit.setVisibility(View.GONE);
        }

        //On Click
        faqs.setOnClickListener(this);
        about_us.setOnClickListener(this);
        manage_address.setOnClickListener(this);
        change_password.setOnClickListener(this);
        profile_update.setOnClickListener(this);

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
        //Layout change_password on click
        else if(view.getId() == R.id.change_password){
            openDialog();
        }
        //Button profile_update on click
        else if(view.getId() == R.id.profile_update){
            String name,number;
            name = settings_edit_name.getEditText().getText().toString();
            number = settings_edit_number.getEditText().getText().toString();

            if(name.isEmpty()){
                settings_edit_name.setError(Constants.error);
            }else {
                Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                Matcher matcher = pattern.matcher(name);
                boolean tf = matcher.matches();
                settings_edit_name.setError(null);
                if (tf) {
                    if(number.isEmpty()){
                        settings_edit_number.setError(Constants.error);
                    }else{
                        settings_edit_number.setError(null);
                        if(number.length() == 10){
                            top_edit.setVisibility(View.GONE);
                        }else {
                            settings_edit_number.setError(Constants.error);
                        }
                    }
                } else {
                    settings_edit_name.setError(Constants.invalid_name);
                }
            }
        }
    }

    public void openDialog() {
        ForgetPasswordDialog exampleDialog = new ForgetPasswordDialog("Change Password");
        exampleDialog.show(getActivity().getSupportFragmentManager(), "dialog");
    }
}