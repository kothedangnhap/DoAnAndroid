package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import Model.BestDeal;

public class BestDealAdapter extends FirebaseRecyclerAdapter<BestDeal,BestDealAdapter.MyViewHodel> {



    public BestDealAdapter(@NonNull FirebaseRecyclerOptions<BestDeal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHodel holder, int position, @NonNull BestDeal bestDeal) {
        holder.bestdeal_name.setText(bestDeal.getName());
        Glide.with(holder.bestdeal_img.getContext()).load(bestDeal.getImage()).into(holder.bestdeal_img);
    }


    @NonNull
    @Override
    public MyViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bestdeal_item_list,parent,false);
        return new MyViewHodel(view);
    }

    public class MyViewHodel extends RecyclerView.ViewHolder {
        ImageView bestdeal_img;
        TextView bestdeal_name;
        public MyViewHodel(@NonNull View itemView) {
            super(itemView);
            bestdeal_img = itemView.findViewById(R.id.bestdeal_img);
            bestdeal_name = itemView.findViewById(R.id.bestdeal_name);
        }
    }
}
