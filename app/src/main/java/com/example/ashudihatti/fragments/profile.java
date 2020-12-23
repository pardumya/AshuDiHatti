package com.example.ashudihatti.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ashudihatti.ForgetPasswordDialog;
import com.example.ashudihatti.R;
import com.example.ashudihatti.constants.Constants;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profile extends AppCompatActivity implements View.OnClickListener {

    ImageView profile_back_button;
    EditText settings_edit_email;
    SharedPreferences preferences;
    String user_name,user_email,user_phone;
    Button profile_update,change_password;
    TextInputLayout settings_edit_name,settings_edit_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //SharedPreferences
        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        user_name = preferences.getString("name",null);
        user_email = preferences.getString("email",null);
        user_phone = preferences.getString("phone",null);
        //TextInputLayout
        settings_edit_name = findViewById(R.id.settings_edit_name);
        settings_edit_name.getEditText().setText(user_name);
        settings_edit_number = findViewById(R.id.settings_edit_number);
        settings_edit_number.getEditText().setText(user_phone);
        //EditText
        settings_edit_email = findViewById(R.id.settings_edit_email);
        settings_edit_email.setText(user_email);
        //Button
        change_password = findViewById(R.id.change_password);
        profile_update = findViewById(R.id.profile_update);
        //ImageView
        profile_back_button = findViewById(R.id.profile_back_button);

        //On Click
        profile_back_button.setOnClickListener(this);
        change_password.setOnClickListener(this);
        profile_update.setOnClickListener(this);

    }

    public void openDialog() {
        ForgetPasswordDialog exampleDialog = new ForgetPasswordDialog("Change Password");
        exampleDialog.show(profile.this.getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.profile_back_button){
            finish();
        }//Layout change_password on click
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
                            Toast.makeText(this, "change", Toast.LENGTH_SHORT).show();
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
}