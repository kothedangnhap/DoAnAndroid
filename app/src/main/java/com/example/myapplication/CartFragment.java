package com.example.myapplication;


import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import Model.Cart;
import Model.Foods;
import Model.User;

public class CartFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private View cartView;
    private RecyclerView myCartList;
    private DatabaseReference cartRef;
    private FirebaseAuth mAuth;
    private String currentUserID;
    Button btn;
    ArrayList<Foods> foodsArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        foodsArrayList = new ArrayList<>();
        cartView = inflater.inflate(R.layout.cart_fragmemt,
                container, false);
        myCartList = cartView.findViewById(R.id.cart_list);
        btn = cartView.findViewById(R.id.btnPayment);

        myCartList.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_food = database.getReference("Foods");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_food.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Foods foods = snapshot.getValue(Foods.class);
                        foodsArrayList.add(foods);
                        String f = foods.description;
                        btn.setText(foods.description);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        return cartView;


    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Foods>()
//                .setQuery(cartRef, Foods.class)
//                .build();
//        FirebaseRecyclerAdapter<Foods,CartViewHolder> adapter = new FirebaseRecyclerAdapter<Foods, CartViewHolder>(options)  {
//            @Override
//            protected void onBindViewHolder(@NonNull final CartViewHolder holder, int position, @NonNull final Foods model) {
//                cartRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String description = snapshot.child("description").getValue().toString();
//                        String price = snapshot.child("description").getValue().toString();
//                        String name = snapshot.child("name").getValue().toString();
//
//                        holder.name.setText(name);
//                        holder.description.setText(description);
//                        holder.price.setText(price);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_display,parent,false);
//               CartViewHolder viewHolder = new CartViewHolder(view);
//               return  viewHolder;
//            }
//        };
//
//        myCartList.setAdapter(adapter);
//        adapter.startListening();
//    }
//
//    public static class CartViewHolder extends  RecyclerView.ViewHolder{
//        TextView description, price, name;
//        ImageView image;
//
//        public CartViewHolder(@NonNull View itemView ){
//            super(itemView);
//            description = itemView.findViewById(R.id.description);
//            price = itemView.findViewById(R.id.price);
//            name =itemView.findViewById(R.id.menu_name);
//        }
//    }

}

