package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_cart:
                        Intent cartDetail = new Intent(Home.this, CartActivity.class);
                        startActivity(cartDetail);
                        break;

                    case  R.id.nav_sign_out:
                        /// code for signin
                        break;
                    case R.id.nav_category:
                        //code for category
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
