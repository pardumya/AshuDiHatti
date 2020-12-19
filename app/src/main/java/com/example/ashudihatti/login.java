package com.example.ashudihatti;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    Button register,login_button,forget_password;
    ImageView login_logo;
    TextInputLayout login_editText_PersonName,login_editText_Password;
    ImageButton drop;
    //String
    String email,password,user_id,user_email,user_phone,user_name;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(this);

        //SharedPreferences
        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        editor = preferences.edit();

        //ImageView Id
        login_logo = findViewById(R.id.login_logo);
        //TextInputLayout Id
        login_editText_PersonName = findViewById(R.id.login_editText_PersonName);
        login_editText_Password = findViewById(R.id.login_editText_Password);
        //Button Id
        register = findViewById(R.id.register);
        login_button = findViewById(R.id.login_button);
        forget_password = findViewById(R.id.forget_password);

        //ImageButton Id
        drop = findViewById(R.id.drop);

        //ImageButton on click
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(forget_password.getVisibility() == View.VISIBLE){
                    forget_password.setVisibility(View.GONE);
                    drop.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }else{
                    forget_password.setVisibility(View.VISIBLE);
                    drop.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });

        //button on click
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View,String>(login_logo,"loading_logo_image");
                pairs[1] = new Pair<View,String>(login_editText_PersonName,"user_email");
                pairs[2] = new Pair<View,String>(login_editText_PersonName,"user_password");
                pairs[3] = new Pair<View,String>(login_button,"login");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = login_editText_PersonName.getEditText().getText().toString().trim();
                password = login_editText_Password.getEditText().getText().toString().trim();

                if(email.isEmpty()){
                    login_editText_PersonName.setError(Constants.error);
                }else{
                    login_editText_PersonName.setError(null);
                    Pattern pattern1 = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                    Matcher matcher1 = pattern1.matcher(email);
                    if(matcher1.matches()){
                        if(password.isEmpty()){
                            login_editText_Password.setError(Constants.error);
                        }else{
                            login_editText_Password.setError(null);
                            makeJsonObjReq(email,password);
                        }
                    }else{
                        login_editText_PersonName.setError(Constants.invalid_email);
                    }
                }
            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        ForgetPasswordDialog exampleDialog = new ForgetPasswordDialog("Forget Password");
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //////  user login check  Function //////

    private void makeJsonObjReq(String email, String password) {
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);
        postParam.put("password", password);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "api", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            user_id = response.getString("id");
                            user_name = response.getString("name");
                            user_email = response.getString("email");
                            user_phone = response.getString("phone");

                            editor.putString("id",user_id);
                            editor.putString("name",user_name);
                            editor.putString("email",user_email);
                            editor.putString("phone",user_phone);
                            editor.apply();
                            startActivity(new Intent(login.this, index.class));
                        } catch (JSONException e) {
                            Log.d("error",e.getMessage());
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

        //////  On Back Press Function //////
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(login.this,index.class));
    }
}