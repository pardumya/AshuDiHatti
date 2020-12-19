package com.example.ashudihatti;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashudihatti.constants.Constants;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    Button login,sign_up_button,resend_email_button;
    ImageView register_logo;
    CountryCodePicker countryCodeHolder;
    ImageButton register_drop;
    TextInputLayout register_user_name,register_user_email,register_user_phone,register_user_password,register_user_confirm_password,register_email_verify;
    LinearLayout register_linearLayout,email_verify_layout;
    ProgressBar register_progress_bar;
    RequestQueue queue;
    String otp,country_code,name,email,number,password,confirm_password;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        queue = Volley.newRequestQueue(this);
        //CountryCodePicker
        countryCodeHolder = findViewById(R.id.countryCodeHolder);
        country_code = countryCodeHolder.getDefaultCountryCode();
        countryCodeHolder.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country_code = countryCodeHolder.getSelectedCountryCode();
            }
        });
        //ImageButton
        register_drop = findViewById(R.id.register_drop);
        //ProgressBar
        register_progress_bar = findViewById(R.id.register_progress_bar);
        //LinearView
        register_linearLayout = findViewById(R.id.register_linearLayout);
        email_verify_layout = findViewById(R.id.email_verify_layout);
        //ImageView
        register_logo = findViewById(R.id.register_logo);
        //TextInputLayout
        register_user_name = findViewById(R.id.register_user_name);
        register_user_email = findViewById(R.id.register_user_email);
        register_user_phone = findViewById(R.id.register_user_phone);
        register_user_password = findViewById(R.id.register_user_password);
        register_user_confirm_password = findViewById(R.id.register_user_confirm_password);
        register_email_verify = findViewById(R.id.register_email_verify);
        //Button Id
        login = findViewById(R.id.login_here);
        sign_up_button = findViewById(R.id.sign_up_button);
        resend_email_button = findViewById(R.id.resend_email_button);

        //Button on click
        resend_email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_verify(email);
            }
        });

        register_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countryCodeHolder.getVisibility() == View.GONE){
                    countryCodeHolder.setVisibility(View.VISIBLE);
                    register_drop.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }else {
                    register_drop.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    countryCodeHolder.setVisibility(View.GONE);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(register.this,login.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View,String>(register_logo,"loading_logo_image");
                pairs[1] = new Pair<View,String>(register_user_name,"user_email");
                pairs[2] = new Pair<View,String>(register_user_email,"user_password");
                pairs[3] = new Pair<View,String>(login,"skip");
                pairs[4] = new Pair<View,String>(sign_up_button,"login");
                final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(register.this,pairs);

                startActivity(intent,options.toBundle());
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sign_up_button.getText().equals("Verify")){
                    if(register_email_verify.getEditText().getText().toString().equals("")){
                        register_email_verify.setError(Constants.error);
                    }else {
                        register_email_verify.setError(null);
                        Log.d("data",otp+register_email_verify.getEditText().getText().toString());
                        if(otp.equals(register_email_verify.getEditText().getText().toString())){
                            register_progress_bar.setVisibility(View.VISIBLE);
                            post_data(register_user_name.getEditText().getText().toString(),register_user_email.getEditText().getText().toString(),register_user_phone.getEditText().getText().toString(),register_user_password.getEditText().getText().toString());
                            register_email_verify.setError(null);
                        }else{
                            Toast.makeText(register.this, "hh", Toast.LENGTH_SHORT).show();
                            register_email_verify.setError(Constants.invalid_otp);
                        }
                    }
                }else{
                    name = Objects.requireNonNull(register_user_name.getEditText()).getText().toString();
                    email = Objects.requireNonNull(register_user_email.getEditText()).getText().toString();
                    number = Objects.requireNonNull(register_user_phone.getEditText()).getText().toString();
                    password = Objects.requireNonNull(register_user_password.getEditText()).getText().toString();
                    confirm_password = Objects.requireNonNull(register_user_confirm_password.getEditText()).getText().toString();

                    if(name.isEmpty()){
                        register_user_name.setError(Constants.error);
                    }else{
                        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                        Matcher matcher = pattern.matcher(name);
                        boolean tf = matcher.matches();
                        if(tf){
                            if(name.length() >= 3){
                                register_user_name.setError(null);
                                if(email.isEmpty()){
                                    register_user_email.setError(Constants.error);
                                }else{
                                    Pattern pattern1 = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                                    Matcher matcher1 = pattern1.matcher(email);
                                    if(matcher1.matches()){
                                        register_user_email.setError(null);
                                        if(number.isEmpty()){
                                            register_user_phone.setError(Constants.error);
                                        }else{
                                            if(number.length() == 10){
                                                register_user_phone.setError(null);
                                                if(password.isEmpty()){
                                                    register_user_password.setError(Constants.error);
                                                }else{
                                                    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                                                    Pattern pattern3 = Pattern.compile(PASSWORD_PATTERN);
                                                    Matcher matcher3 = pattern3.matcher(password);
                                                    if(matcher3.matches()){
                                                        register_user_password.setError(null);
                                                        if(confirm_password.isEmpty()){
                                                            register_user_confirm_password.setError(Constants.error);
                                                        }else{
                                                            if(password.equals(confirm_password)){
                                                                register_user_confirm_password.setError(null);
                                                                register_progress_bar.setVisibility(View.VISIBLE);
                                                                email_verify(email);
                                                            }else{
                                                                register_user_confirm_password.setError(Constants.password_match);
                                                            }
                                                        }
                                                    }else {
                                                        register_user_password.setError(Constants.password_invalid);
                                                    }

                                                }
                                            }else{
                                                register_user_phone.setError(Constants.invalid_contact);
                                            }
                                        }
                                    }else{
                                        register_user_email.setError(Constants.invalid_email);
                                    }
                                }
                            }else{
                                register_user_name.setError(Constants.name_length);
                            }
                        }else {
                            register_user_name.setError(Constants.invalid_name);
                        }
                    }
                }
            }
        });
    }

    private void email_verify(String email) {
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "api", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("data", String.valueOf(response));
                        if(!response.equals("null")){
                            register_progress_bar.setVisibility(View.GONE);

                            register_linearLayout.setVisibility(View.GONE);
                            email_verify_layout.setVisibility(View.VISIBLE);
                            resend_email_button.setVisibility(View.VISIBLE);

                            sign_up_button.setText("Verify");
                            try {
                                otp = String.valueOf(response.get("error"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        register_progress_bar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Email Already Register", Toast.LENGTH_SHORT).show();
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


    // post user register data function
    public void post_data(String name, String email, String number, String password){
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("customer_name", name);
        postParam.put("email", email);
        //postParam.put("phone", "+"+country_code+number);
        postParam.put("phone", number);
        postParam.put("password", password);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "api", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if(!response.equals("null")){
                            register_progress_bar.setVisibility(View.GONE);
                            startActivity(new Intent(register.this, login.class));
                            Log.d("data", String.valueOf(response));
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                register_progress_bar.setVisibility(View.GONE);
                Toast.makeText(register.this, "User Not Create", Toast.LENGTH_SHORT).show();
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

    // on back press function
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(register.this,index.class));
    }
}