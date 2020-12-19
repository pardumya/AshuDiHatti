package com.example.ashudihatti.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ashudihatti.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class manage_address extends AppCompatActivity {

    //TextInputLayout
    TextInputLayout name,email,country,state,primary_address,secondary_address,city,zip_code,phone_number;
    //Button
    Button save_address;
    //String
    String name_string,email_string,country_string,state_string,primary_address_string,secondary_address_string,city_string,zip_code_string,phone_number_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);

        //TextInputLayout
        name = findViewById(R.id.manage_address_full_name);
        email = findViewById(R.id.manage_address_email);
        country = findViewById(R.id.manage_address_country);
        state = findViewById(R.id.manage_address_state);
        primary_address = findViewById(R.id.manage_address_primary_address);
        secondary_address = findViewById(R.id.manage_address_secondary_address);
        city = findViewById(R.id.manage_address_city);
        zip_code = findViewById(R.id.manage_address_zip_code);
        phone_number = findViewById(R.id.manage_address_phone_number);

        //Button
        save_address = findViewById(R.id.manage_address_save_address);

        //Button on click
        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_string = Objects.requireNonNull(name.getEditText()).getText().toString();
                email_string = Objects.requireNonNull(email.getEditText()).getText().toString();
                country_string = Objects.requireNonNull(country.getEditText()).getText().toString();
                state_string = Objects.requireNonNull(state.getEditText()).getText().toString();
                primary_address_string = Objects.requireNonNull(primary_address.getEditText()).getText().toString();
                secondary_address_string = Objects.requireNonNull(secondary_address.getEditText()).getText().toString();
                city_string = Objects.requireNonNull(city.getEditText()).getText().toString();
                zip_code_string = Objects.requireNonNull(zip_code.getEditText()).getText().toString();
                phone_number_string = Objects.requireNonNull(phone_number.getEditText()).getText().toString();

                //TextInputLayout validation
                if(name_string.isEmpty()){
                    Toast.makeText(manage_address.this, "Empty Name Field", Toast.LENGTH_SHORT).show();
                }else{
                    Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                    Matcher matcher = pattern.matcher(name_string);
                    boolean tf = matcher.matches();
                    if(tf){
                        if(name_string.length() >= 4){
                            if(email_string.isEmpty()){
                                Toast.makeText(manage_address.this, "Empty Email Field", Toast.LENGTH_SHORT).show();
                            }else{
                                if(email_string.contains("@gmail.com")){
                                    if(country_string.isEmpty()){
                                        Toast.makeText(manage_address.this, "Empty Country Field", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if(state_string.isEmpty()){
                                            Toast.makeText(manage_address.this, "Empty State Field", Toast.LENGTH_SHORT).show();
                                        }else{
                                            if(primary_address_string.isEmpty()){
                                                Toast.makeText(manage_address.this, "Empty Primary Address Field", Toast.LENGTH_SHORT).show();
                                            }else{
                                                if(secondary_address_string.isEmpty()){
                                                    Toast.makeText(manage_address.this, "Empty Secondary Field", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    if(city_string.isEmpty()){
                                                        Toast.makeText(manage_address.this, "Empty City Field", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        if(zip_code_string.isEmpty()){
                                                            Toast.makeText(manage_address.this, "Empty Zip-Code Field", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            if(phone_number_string.isEmpty()){
                                                                Toast.makeText(manage_address.this, "Empty Phone-Number Field", Toast.LENGTH_SHORT).show();
                                                            }else{
                                                                Toast.makeText(manage_address.this, "Save", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    Toast.makeText(manage_address.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            Toast.makeText(manage_address.this, "Minimum user-name length should be(4)", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(manage_address.this, "Enter valid user-name", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}