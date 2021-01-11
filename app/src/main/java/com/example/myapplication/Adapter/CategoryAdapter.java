package com.example.myapplication.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.FoodDetail;
import com.example.myapplication.R;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.ChangeEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Common.Common;
import Model.Category;
import Model.Food;

public class CategoryAdapter extends FirebaseRecyclerAdapter<Food,CategoryAdapter.myviewholder> implements Filterable
{
    private List<Food> foodList;
    private  List<Food> foodListFull;
    Fragment fragment;
    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<Food> options) {
        super(options);
        foodList = new ArrayList<>();
        this.getSnapshots().addChangeEventListener(new ChangeEventListener() {
            @Override
            public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
                foodList.add(snapshot.getValue(Food.class));
            }
            @Override
            public void onDataChanged() {
                foodListFull = new ArrayList<>(foodList);
            }

            @Override
            public void onError(@NonNull DatabaseError databaseError) {

            }
        });

//        FirebaseDatabase.getInstance().getReference().child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Food food = postSnapshot.getValue(Food.class);
//                    foodList.add(food);
//                }
//                foodListFull = new ArrayList<>(foodList);
//
//
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Food food) {
            String FoodID = getRef(position).getKey();
            holder.food_name.setText(food.getName());
            Glide.with(holder.food_img.getContext()).load(food.getImage()).into(holder.food_img);
            holder.category_id = food.getMenuId();
            holder.FoodID = FoodID;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView food_img;
        TextView food_name;
        String category_id;
        String FoodID;

        public myviewholder(@NonNull final View itemView) {
            super(itemView);

            food_img = itemView.findViewById(R.id.food_img);
            food_name = itemView.findViewById(R.id.food_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext().getApplicationContext(), FoodDetail.class);
                    intent.putExtra("foodId",FoodID);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
      return foodList.size();
    }

    @Override
    public Filter getFilter() {
        return foodListFiler;
    }
    private  Filter foodListFiler = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           List<Food> filteredList = new ArrayList<>();
           if (constraint == null || constraint.length() ==0){
                filteredList.addAll(foodListFull);
           }else {
               String filterPattern = constraint.toString().trim().toLowerCase();
               for(Food item : foodListFull){
                   if(item.getName().toLowerCase().contains(filterPattern)){
                       item.getName();
                       filteredList.add(item);
                   }
               }

           }
           FilterResults results = new FilterResults();
           results.values = filteredList;
           return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                foodList.clear();
                foodList.addAll((List<Food>)results.values);
                notifyDataSetChanged();
        }
    };

}

