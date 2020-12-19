package com.example.ashudihatti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

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


public class ForgetPasswordDialog extends AppCompatDialogFragment {
    private TextInputLayout forget_password_email,forget_password_old_password,forget_password_new_password;
    RequestQueue queue;
    String name,email,old_password,new_password;
    public ForgetPasswordDialog(String change_password) {
        name = change_password;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        queue = Volley.newRequestQueue(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.forget_password_layout, null);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(name)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = forget_password_email.getEditText().getText().toString();
                old_password = forget_password_old_password.getEditText().getText().toString();
                new_password = forget_password_old_password.getEditText().getText().toString();
                if(name.equals("Forget Password")){
                    if(email.equals("")){
                        forget_password_email.setError(Constants.error);
                    }else{
                        forget_password_email.setError(null);
                        Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                        Matcher matcher = pattern.matcher(email);
                        if(matcher.matches()){
                            forget_password_email.setError(null);
                            forget_password(email);
                            dialog.dismiss();
                        }else{
                            forget_password_email.setError(Constants.invalid_email);
                        }
                    }
                }else{
                    if(email.equals("")){
                        forget_password_email.setError(Constants.error);
                    }else{
                        forget_password_email.setError(null);
                        Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                        Matcher matcher = pattern.matcher(email);
                        if(matcher.matches()){
                            forget_password_email.setError(null);
                            if(old_password.isEmpty()){
                                forget_password_old_password.setError(Constants.error);
                            }else{
                                if(new_password.isEmpty()){
                                    forget_password_new_password.setError(Constants.error);
                                }else{
                                    forget_password_new_password.setError(null);
                                    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                                    Pattern pattern1 = Pattern.compile(PASSWORD_PATTERN);
                                    Matcher matcher1 = pattern1.matcher(new_password);
                                    if(matcher1.matches()){
                                        forget_password_new_password.setError(null);
                                        dialog.dismiss();
                                        Toast.makeText(getActivity(), "Password Change Successfully.", Toast.LENGTH_SHORT).show();
                                        change_password(email);
                                    }else {
                                        forget_password_new_password.setError(Constants.password_invalid);
                                    }
                                }
                            }
                        }else{
                            forget_password_email.setError(Constants.invalid_email);
                        }
                    }
                }
            }
        });

        forget_password_email = view.findViewById(R.id.forget_password_email);
        forget_password_old_password = view.findViewById(R.id.forget_password_old_password);
        forget_password_new_password = view.findViewById(R.id.forget_password_new_password);

        if(name.equals("Forget Password")){
            forget_password_old_password.setVisibility(View.GONE);
            forget_password_new_password.setVisibility(View.GONE);
        }else{
            forget_password_old_password.setVisibility(View.VISIBLE);
            forget_password_new_password.setVisibility(View.VISIBLE);
        }

        return dialog;
    }

    private void forget_password(String email) {
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "api", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if(!response.equals("null")){
                            Toast.makeText(getActivity(), "New Password Sent To Your Registered Email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    private void change_password(String email) {
        final Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);
        postParam.put("oldpassword", old_password);
        postParam.put("newpassword", new_password);

        Log.d("data", String.valueOf(postParam));

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "api", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        if(!response.equals("null")){
                            try {
                                Toast.makeText(getActivity(), response.getString("user"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("data", String.valueOf(response));
                        }else{
                            Log.d("error", String.valueOf(response));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Email Already Register", Toast.LENGTH_SHORT).show();
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
}
