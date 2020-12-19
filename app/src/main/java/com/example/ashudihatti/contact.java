package com.example.ashudihatti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class contact extends AppCompatActivity implements OnMapReadyCallback {

    ImageView contact_back,contact_location_icon,contact_call_icon,contact_email_icon;
    TextView contact_text;
    GoogleMap mMap;
    Marker marker;

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

        //ImageView on click
        contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        contact_location_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact_text.setVisibility(View.VISIBLE);
                contact_text.setText(location);
            }
        });

        contact_call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact_text.setVisibility(View.VISIBLE);
                contact_text.setText(call);
            }
        });
        contact_email_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact_text.setVisibility(View.VISIBLE);
                contact_text.setText(email);
            }
        });


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
}