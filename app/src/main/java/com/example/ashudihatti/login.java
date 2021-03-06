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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button register,login_button,forget_password;
    ImageView login_logo;
    TextInputLayout login_editText_PersonName,login_editText_Password;
    ImageButton drop;
    String email,password,user_id,user_email,user_phone,user_name,message;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    ProgressBar login_progressbar;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(this);

        //ProgressBar
        login_progressbar = findViewById(R.id.login_progressbar);

        //SharedPreferences
        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

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

        //On Click
        drop.setOnClickListener(this);
        register.setOnClickListener(this);
        login_button.setOnClickListener(this);
        forget_password.setOnClickListener(this);

    }

    public void openDialog() {
        ForgetPasswordDialog exampleDialog = new ForgetPasswordDialog("Forget Password");
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //////  user login check  Function //////

    private void user_login(String email, String password) {

        final Map<String, String> postParam= new HashMap<>();
        postParam.put("email", email);
        postParam.put("password", password);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constants.login_api, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            message = "d";

                            user_id = response.getString("id");
                            user_name = response.getString("name");
                            user_email = response.getString("email");
                            user_phone = response.getString("phone");

                            editor = preferences.edit();
                            editor.putString("id",user_id);
                            editor.putString("name",user_name);
                            editor.putString("email",user_email);
                            editor.putString("phone",user_phone);
                            editor.apply();

                            startActivity(new Intent(login.this, index.class));

                        } catch (JSONException e) {
                            Log.d("datta","s"+e.getMessage());
                            message = "s"+e.getMessage();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                message = "Try-Again Later";
                Log.d("datta","s"+error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        login_progressbar.setVisibility(View.GONE);
        queue.add(jsonObjReq);
    }

        //////  On Back Press Function //////
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(login.this,index.class));
    }

    @Override
    public void onClick(View view) {
        //drop button
        if(view.getId() == R.id.drop){
            if(forget_password.getVisibility() == View.VISIBLE){
                forget_password.setVisibility(View.GONE);
                drop.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            }else{
                forget_password.setVisibility(View.VISIBLE);
                drop.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }
        }
        //register button
        else if(view.getId() == R.id.register){
            Intent intent = new Intent(login.this,register.class);
            Pair[] pairs = new Pair[4];
            pairs[0] = new Pair<View,String>(login_logo,"loading_logo_image");
            pairs[1] = new Pair<View,String>(login_editText_PersonName,"user_email");
            pairs[2] = new Pair<View,String>(login_editText_PersonName,"user_password");
            pairs[3] = new Pair<View,String>(login_button,"login");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
            startActivity(intent,options.toBundle());
        }
        //login button
        else if(view.getId() == R.id.login_button){
            login_progressbar.setVisibility(View.VISIBLE);
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
                        user_login(email,password);
                    }
                }else{
                    login_editText_PersonName.setError(Constants.invalid_email);
                }
            }
        }
        //forget password button
        else if(view.getId() == R.id.forget_password){
            openDialog();
        }
    }
}