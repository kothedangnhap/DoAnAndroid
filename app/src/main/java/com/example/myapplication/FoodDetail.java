package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.bumptech.glide.Glide;

import Common.Common;
import Model.CartItem;
import Model.Food;
import Model.isSave;

public class FoodDetail extends AppCompatActivity {
        private DatabaseReference FoodRef;

        TextView txt_description,txt_detail_food_name,txt_detail_food_price;
        ImageView img_food;
        String staticPrice;


        CollapsingToolbarLayout collapsingToolbarLayout;
        ElegantNumberButton numberButton;
        FloatingActionButton btn_detail_food_AddToCart;

       String id;
       CartItem cartItem;

        private DatabaseReference cartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        numberButton = findViewById(R.id.btn_detail_food_plus);
        img_food = findViewById(R.id.img_food);
        txt_detail_food_name = findViewById(R.id.txt_detail_food_name);
        txt_description = findViewById(R.id.txt_detail_food_description);
        btn_detail_food_AddToCart = findViewById(R.id.btn_detail_food_AddToCart);
        txt_detail_food_price = findViewById(R.id.txt_detail_food_price);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandeAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        final Intent foodId = getIntent();

         id =  foodId.getStringExtra("foodId");
        FoodRef = FirebaseDatabase.getInstance().getReference().child("Foods").child(id);
        cartRef = FirebaseDatabase.getInstance().getReference().child("Cart");
        FoodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String description = snapshot.child("description").getValue().toString();
                String name = snapshot.child("name").getValue().toString();
                String price = snapshot.child("price").getValue().toString();
                String img = snapshot.child("image").getValue().toString();
                txt_detail_food_name.setText(name);
                txt_description.setText(description);
                txt_detail_food_price.setText(price);
                staticPrice = price;
                Glide.with(img_food.getContext()).load(img).into(img_food);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(Common.currentUser.getPhone()).hasChild(id)){
                    btn_detail_food_AddToCart.setClickable(false);
                    btn_detail_food_AddToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(FoodDetail.this, " Food is added to your cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                    int number = Integer.parseInt(numberButton.getNumber());

                    int result = number *Integer.parseInt(staticPrice);
                   txt_detail_food_price.setText(Integer.toString(result));
            }
        });
        btn_detail_food_AddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_detail_food_AddToCart.setClickable(false);
                cartRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        isSave i = new isSave("saved");
                        cartRef.child(Common.currentUser.getPhone()).child(id).setValue(i);

                        if(btn_detail_food_AddToCart.isClickable())
                            Toast.makeText(FoodDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}