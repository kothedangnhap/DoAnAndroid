package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,new homeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {



                    //Dat
                    case R.id.nav_cart:

                            selectedFragment = new CartFragment();

                        break;
                    case  R.id.nav_sign_out:
                        /// code for signin
                        break;
                    case R.id.nav_category:
                        //code for category
                        break;
                    default:
                        selectedFragment = new homeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,selectedFragment).commit();
                return false;
            }
        });
    }
}
