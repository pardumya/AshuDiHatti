package com.example.ashudihatti;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class index extends AppCompatActivity implements View.OnClickListener{
    private AppBarConfiguration mAppBarConfiguration;
    long backPressedTime;
    Toast backToast;
    public static String FACEBOOK_URL = "https://www.facebook.com/Ashu-di-Hatti-phagwara-104793427965220";
    public static String FACEBOOK_PAGE_ID = "Ashu-di-Hatti-phagwara-104793427965220";

    ImageView facebook,instagram,twitter,pinterest;
    Button header_login,header_signup;

    SharedPreferences preferences;
    String user_id;
    DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton fab;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        user_id = preferences.getString("id",null);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_product)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Drawer Heading
        header = navigationView.getHeaderView(0);

        //Button Id
        header_login = header.findViewById(R.id.header_login);
        header_signup = header.findViewById(R.id.header_signup);
        //ImageView Id
        facebook = header.findViewById(R.id.facebook);
        instagram = header.findViewById(R.id.instagram);
        twitter = header.findViewById(R.id.twitter);
        pinterest = header.findViewById(R.id.pinterest);

        if(user_id != null){
            header_login.setVisibility(View.GONE);
            header_signup.setText("Log-Out");
        }

        //On Click
        fab.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        twitter.setOnClickListener(this);
        pinterest.setOnClickListener(this);
        header_login.setOnClickListener(this);
        header_signup.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_cart){
            startActivity(new Intent(index.this,cart.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            finishAffinity();
        }else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        //FloatingActionButton on click
        if(view.getId() == R.id.fab){
            startActivity(new Intent(index.this,contact.class));
        }
        //Social Media Accounts
        else if(view.getId() == R.id.facebook){
            getFacebookPageURL(index.this);

            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(index.this);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
            drawer.close();
        }else if(view.getId() == R.id.instagram){
            Uri uri = Uri.parse("https://www.instagram.com/ashudihattiphagwara/?hl=en");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/ashudihattiphagwara/?hl=en")));
            }
        }else if(view.getId() == R.id.twitter){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/HattiAshu")));
            }catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/HattiAshu")));
            }
        }else if(view.getId() == R.id.pinterest){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("pinterest://www.pinterest.com/aashudihattiphagwara")));
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/aashudihattiphagwara")));
            }
        }
        //Login or Signup Button
        else if(view.getId() == R.id.header_login){
            startActivity(new Intent(index.this,login.class));
        }else if(view.getId() == R.id.header_signup){
            if(header_signup.getText().equals("Log-Out")){
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                header_login.setVisibility(View.VISIBLE);
                header_signup.setText("Sign-Up");
            }else {
                startActivity(new Intent(index.this,register.class));
            }
        }
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}