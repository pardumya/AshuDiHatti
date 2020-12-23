package com.example.ashudihatti;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class contact extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    ImageView contact_back,contact_location_icon,contact_call_icon,contact_email_icon;
    TextInputLayout contact_username,contact_user_email,contact_user_number,contact_user_query;
    TextView contact_text;
    Button contact_query_submit;
    GoogleMap mMap;
    Marker marker;
    RequestQueue queue;

    String email = "info@naveenstores.com";
    String call = "098720 14014";
    String location = "Cinema road, phagwara (110.59 km) 0786 Phagwara, Punjab region, India";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //TextView Id
        contact_text =findViewById(R.id.contact_text);

        //ImageView ID
        contact_back = findViewById(R.id.contact_back);
        contact_location_icon = findViewById(R.id.contact_location_icon);
        contact_call_icon = findViewById(R.id.contact_call_icon);
        contact_email_icon = findViewById(R.id.contact_email_icon);
        //EditText Id
        contact_username = findViewById(R.id.contact_username);
        contact_user_email = findViewById(R.id.contact_user_email);
        contact_user_number = findViewById(R.id.contact_user_number);
        contact_user_query = findViewById(R.id.contact_user_query);

        //Button Id
        contact_query_submit = findViewById(R.id.contact_query_submit);

        queue = Volley.newRequestQueue(this);

        //On Click
        contact_back.setOnClickListener(this);
        contact_location_icon.setOnClickListener(this);
        contact_call_icon.setOnClickListener(this);
        contact_email_icon.setOnClickListener(this);
        contact_query_submit.setOnClickListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(marker!=null){
            marker.remove();
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.223105,75.767119);
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Ashu Di Hatti"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMinZoomPreference(12.0f);
    }
    public void onMapReady(View view) {

        if(marker!=null){
            marker.remove();
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.223105,75.767119);
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Ashu Di Hatti"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMinZoomPreference(12.0f);
    }

    @Override
    public void onClick(View view) {
        //back button
        if(view.getId() == R.id.contact_back){
            finish();
        }
        //Contact info
        else if(view.getId() == R.id.contact_location_icon){
            contact_text.setVisibility(View.VISIBLE);
            contact_text.setText(location);
        }else if(view.getId() == R.id.contact_call_icon){
            contact_text.setVisibility(View.VISIBLE);
            contact_text.setText(call);
        }else if(view.getId() == R.id.contact_email_icon){
            contact_text.setVisibility(View.VISIBLE);
            contact_text.setText(email);
        }
        //submit button
        else if(view.getId() == R.id.contact_query_submit){
            String name,email,number,query;

            name = contact_username.getEditText().getText().toString();
            email = contact_user_email.getEditText().getText().toString();
            number = contact_user_number.getEditText().getText().toString();
            query = contact_user_query.getEditText().getText().toString();

            if(name.isEmpty()){
                contact_username.setError("Empty user-name Field");
            }else{
                contact_username.setError(null);
                if(email.isEmpty()){
                    contact_user_email.setError("Empty E-mail Field");
                }else{
                    contact_user_email.setError(null);
                    if(number.isEmpty()){
                        contact_user_number.setError("Empty Phone-Number Field");
                    }else{
                        contact_user_number.setError(null);
                        if(query.isEmpty()){
                            contact_user_query.setError("Empty Query Field");
                        }else{
                            contact_user_query.setError(null);
                            submit_query(name,email,number,query);
                        }
                    }
                }
            }
        }
    }
    private void submit_query(String name, String email, String number, String query) {
        final Map<String, String> postParam= new HashMap<>();
        postParam.put("name", name);
        postParam.put("email", email);
        postParam.put("phone", number);
        postParam.put("message", query);

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constants.contact_us_api, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            Toast.makeText(contact.this, "Query Submit", Toast.LENGTH_SHORT).show();
                            contact_username.getEditText().setText("");
                            contact_user_email.getEditText().setText("");
                            contact_user_number.getEditText().setText("");
                            contact_user_query.getEditText().setText("");
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
}