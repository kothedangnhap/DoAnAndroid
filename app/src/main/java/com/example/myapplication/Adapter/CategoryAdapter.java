package com.example.myapplication.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.FoodDetail;
import com.example.myapplication.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import Model.Category;
import Model.Food;

public class CategoryAdapter extends FirebaseRecyclerAdapter<Food,CategoryAdapter.myviewholder>
{

    Fragment fragment;
    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<Food> options) {
        super(options);
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
}
