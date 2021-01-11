package com.example.myapplication.Adapter;

import android.content.Intent;
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

import Model.BestDeal;

public class BestDealAdapter extends FirebaseRecyclerAdapter<BestDeal,BestDealAdapter.MyViewHodel> {



    public BestDealAdapter(@NonNull FirebaseRecyclerOptions<BestDeal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHodel holder, int position, @NonNull BestDeal bestDeal) {


        holder.bestdeal_name.setText(bestDeal.getName());
        Glide.with(holder.bestdeal_img.getContext()).load(bestDeal.getImage()).into(holder.bestdeal_img);
        holder.FoodID = bestDeal.getFood_id();
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
        String FoodID;
        public MyViewHodel(@NonNull final View itemView) {
            super(itemView);
            bestdeal_img = itemView.findViewById(R.id.bestdeal_img);
            bestdeal_name = itemView.findViewById(R.id.bestdeal_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext().getApplicationContext(), FoodDetail.class);
                    FoodID = FoodID;
                    intent.putExtra("foodId",FoodID);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
