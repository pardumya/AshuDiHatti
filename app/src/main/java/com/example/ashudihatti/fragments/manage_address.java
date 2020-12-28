package com.example.ashudihatti.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.R;
import com.example.ashudihatti.constants.Constants;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class manage_address extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout name,email,country,state,primary_address,secondary_address,city,zip_code,phone_number;
    Button save_address;
    ImageView manage_address_back_button;
    String name_string,email_string,country_string,state_string,primary_address_string,secondary_address_string,city_string,zip_code_string,phone_number_string,user_url,user_id;
    RequestQueue queue;
    ProgressBar progressBar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);

        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        user_id = preferences.getString("id",null);

        queue = Volley.newRequestQueue(this);

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
        //ImageView
        manage_address_back_button = findViewById(R.id.manage_address_back_button);
        //Button
        save_address = findViewById(R.id.manage_address_save_address);

        //On Click
        manage_address_back_button.setOnClickListener(this);
        save_address.setOnClickListener(this);

    }

    private void set_billing_info(String name_string, String email_string, String country_string, String state_string, String primary_address_string, String secondary_address_string, String city_string, String zip_code_string, String phone_number_string) {
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("name", name_string);
        postParam.put("email", email_string);
        postParam.put("country", country_string);
        postParam.put("state", state_string);
        postParam.put("address1", primary_address_string);
        postParam.put("address2", secondary_address_string);
        postParam.put("city", city_string);
        postParam.put("zip_code", zip_code_string);
        postParam.put("phone", phone_number_string);
        postParam.put("user","http://naveenstores.in/api/customer/"+"4"+"/");

        Log.d("data", String.valueOf(postParam));

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                Constants.address_api, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if(!response.equals("null")){
                            Log.d("data", String.valueOf(response));
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        queue.add(jsonObjReq);
    }

    private void get_billing_info() {
        progressBar = findViewById(R.id.manage_address_progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.address_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray heroArray = new JSONArray(response);

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String customer = heroObject.getString("user");
                                String[] data = customer.split("/");

                                if(data[5].equals(user_id)){
                                    name.getEditText().setText(heroObject.getString("name"));
                                    email.getEditText().setText(heroObject.getString("email"));
                                    country.getEditText().setText(heroObject.getString("country"));
                                    state.getEditText().setText(heroObject.getString("state"));
                                    primary_address.getEditText().setText(heroObject.getString("address1"));
                                    secondary_address.getEditText().setText(heroObject.getString("address2"));
                                    city.getEditText().setText(heroObject.getString("city"));
                                    zip_code.getEditText().setText(heroObject.getString("zip_code"));
                                    phone_number.getEditText().setText(heroObject.getString("phone"));
                                    user_url = heroObject.getString("url");
                                }
                            }

                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        //back button
        if(view.getId() == R.id.manage_address_back_button){
            finish();
        }
        //sign-up button
        else if(view.getId() == R.id.manage_address_save_address){
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
                                                            set_billing_info(name_string,email_string,country_string,state_string,primary_address_string,secondary_address_string,city_string,zip_code_string,phone_number_string);
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
    }
}