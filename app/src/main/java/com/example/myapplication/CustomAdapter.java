package com.example.myapplication;

import android.content.Context;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myviewholder>
{
    private List<Food> foodList;
    public  Context c;
    private  List<Food> foodListFull;
    Fragment fragment;
    public CustomAdapter(@NonNull Context context ,ArrayList<Food> arrayList) {
        this.c = context;
        this.foodList  =arrayList;
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Food  food= foodList.get(position);
        holder.food_name.setText(food.getName());
        holder.FoodID = food.getId();
        Glide.with(holder.food_img.getContext()).load(food.getImage()).into(holder.food_img);
        holder.category_id = food.getMenuId();
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
        String FoodID ="10";

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
    public long getItemId(int position) {
        return position;
    }
}

