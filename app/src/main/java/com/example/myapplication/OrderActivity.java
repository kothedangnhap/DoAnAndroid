package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import Model.User;

public class OrderActivity extends AppCompatActivity {

    MaterialEditText editName,editPhone,editAddress;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        editName = (MaterialEditText)findViewById(R.id.editName);
        editPhone = (MaterialEditText)findViewById(R.id.editPhone);
        editAddress = (MaterialEditText)findViewById(R.id.editAddress);
        btnSave= findViewById(R.id.btnSave);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order = database.getReference("order");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog =new ProgressDialog(OrderActivity.this);
                mDialog.setMessage("please waiting...");
                mDialog.show();
                table_order.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        User user = new User(Objects.requireNonNull(editName.getText()).toString(), editAddress.getText().toString());
                        table_order.child(editPhone.getText().toString()).setValue(user);
                        Toast.makeText(OrderActivity.this,"Order successfully !",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
