package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

import com.example.myapplication.Adapter.CategoryAdapter;
import Model.Category;
import Model.Food;

public class Categories_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private DatabaseReference FoodRef;


    RecyclerView recview;
    CategoryAdapter adapter;

    public Categories_Fragment() {
    }

    public static Categories_Fragment newInstance(String param1, String param2){
        Categories_Fragment fragment = new Categories_Fragment();
        Bundle args =  new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        recview = view.findViewById(R.id.recycler_category);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"),Food.class)
                .build();
        setHasOptionsMenu(true);
        adapter = new CategoryAdapter(options);
        recview.setAdapter(adapter);

        FoodRef = FirebaseDatabase.getInstance().getReference().child("Foods");

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods").orderByChild("foodId").startAt(s).endAt(s+"\uf8ff"),Food.class)
                .build();
        adapter=new CategoryAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView  name;
        ImageView image;
        String id;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.food_name);
            image = itemView.findViewById(R.id.food_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity().getApplicationContext(), FoodDetail.class);
                    intent.putExtra("foodId",id);

                    startActivityForResult(intent,1);

                    startActivity(intent); //or startActivityForResult(REQUEST, intent);
                }
            });
        }
    }
}