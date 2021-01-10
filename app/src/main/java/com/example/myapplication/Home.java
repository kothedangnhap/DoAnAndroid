package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import Common.Common;

public class Home extends AppCompatActivity implements RecyclerView.RecyclerListener {
    BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        toolbar = getSupportActionBar();

        toolbar.setTitle("Hello "+Common.currentUser.getName());

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    toolbar.setTitle("Hello "+Common.currentUser.getName());
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_category:
                    toolbar.setTitle("Categories");

                    fragment = new Categories_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_cart:
                    toolbar.setTitle("Cart");
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
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
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_contain, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {

    }
}
