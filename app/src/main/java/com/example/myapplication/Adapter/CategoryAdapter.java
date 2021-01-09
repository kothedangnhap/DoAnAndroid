package com.example.myapplication.Adapter;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.FoodDetail;
import com.example.myapplication.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.List;

import Common.Common;
import Model.Category;
import Model.Food;

public class CategoryAdapter extends FirebaseRecyclerAdapter<Food,CategoryAdapter.myviewholder>
{
    private DatabaseReference FoodRef;

    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<Food> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Food food) {
        holder.food_name.setText(food.getName());
        Glide.with(holder.food_img.getContext()).load(food.getImage()).into(holder.food_img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list,parent,false);
        FoodRef = FirebaseDatabase.getInstance().getReference().child("Foods");
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView food_img;
        TextView food_name;
        String id;

        public myviewholder(@NonNull final View itemView) {
            super(itemView);

            food_img = itemView.findViewById(R.id.food_img);
            food_name = itemView.findViewById(R.id.food_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), FoodDetail.class);
                    intent.putExtra("foodId",id);

                    itemView.getContext().startActivity(intent); //or startActivityForResult(REQUEST, intent);
                }
            });
        }
    }
}
