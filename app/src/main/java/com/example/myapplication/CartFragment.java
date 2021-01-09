package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.bumptech.glide.Glide;


import Common.Common;
import Model.Cart;
import Model.Food;



public class CartFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private View cartView;
    private RecyclerView myCartList;
    private DatabaseReference cartRef;
    private DatabaseReference FoodRef;
    Button btn;
    String currentUserID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        cartView = inflater.inflate(R.layout.cart_fragmemt, container, false);

        myCartList = (RecyclerView)cartView.findViewById(R.id.cart_list);
        myCartList.setLayoutManager(new LinearLayoutManager(getContext()));

        currentUserID  = Common.currentUser.getPhone(); // get current user when login

        cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(currentUserID);
        FoodRef = FirebaseDatabase.getInstance().getReference().child("Foods");

        //For button payment
        btn = cartView.findViewById(R.id.btnPayment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "please waiting...", Toast.LENGTH_SHORT).show();
                Intent Order = new Intent(getContext(), OrderActivity.class);
                startActivity(Order);

            }
        });
        return cartView;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(cartRef, Food.class)
                .build();

        FirebaseRecyclerAdapter<Food,CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Food, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CartViewHolder holder, int position, @NonNull Food model) {

                String foodID = getRef(position).getKey();
                FoodRef.child(foodID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String description = snapshot.child("description").getValue().toString();
                        String name = snapshot.child("name").getValue().toString();
                        String price = snapshot.child("price").getValue().toString();
                        String img = snapshot.child("image").getValue().toString();
                       holder.description.setText(description);
                       holder.name.setText(name);
                       holder.price.setText(price);
                       Glide.with(holder.image.getContext()).load(img).into(holder.image);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_display,parent,false);
                CartViewHolder viewHolder = new CartViewHolder(view);
                return viewHolder;
            }
        };




        myCartList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class CartViewHolder extends  RecyclerView.ViewHolder{
        TextView description, price, name;
        ImageView image;

        public CartViewHolder(@NonNull View itemView ){
            super(itemView);
            description = itemView.findViewById(R.id.txt_description_cart);
            price = itemView.findViewById(R.id.txt_price_cart);
            name =itemView.findViewById(R.id.txt_name_cart);
            image = itemView.findViewById(R.id.img_img_cart);
        }
    }

}
