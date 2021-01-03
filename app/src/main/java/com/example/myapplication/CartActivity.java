package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_menu);

        MenuItem select = bottomNavigationView.getMenu().findItem(bottomNavigationView.getSelectedItemId());
        select.setChecked(false);
        bottomNavigationView.getMenu().findItem(R.id.nav_cart).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent Home = new Intent(CartActivity.this, Home.class);

                        startActivity(Home);
                    default:
                        break;
                }
                return false;
            }
        });
    }
}