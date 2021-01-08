package com.example.myapplication;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
>>>>>>> c968fbe34a472efa8096276dc985751f4574bdf8

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import Common.Common;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
<<<<<<< HEAD

        bottomNavigationView=findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,new homeFragment()).commit();
=======
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
>>>>>>> c968fbe34a472efa8096276dc985751f4574bdf8
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.nav_menu:

                    break;
                case R.id.nav_cart:
                    selectedFragment = new CartFragment();
                    break;
                case R.id.nav_sign_out:
                    AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
                    builder.setTitle("Signout").setMessage("Do you really want to sign out?").setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Common.currentUser=null;
                            FirebaseAuth.getInstance().signOut();

                            Intent intent=new Intent(Home.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,selectedFragment).commit();
            return false;
        }
    };
}
