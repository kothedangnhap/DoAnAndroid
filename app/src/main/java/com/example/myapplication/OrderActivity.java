package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import Common.Common;
import Model.Order;

public class OrderActivity extends AppCompatActivity {

    MaterialEditText editName,editPhone,editAddress,editNote;
    Button btnSave;
    RadioGroup rdgGroupPayment;
    RadioButton rdbPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editAddress = findViewById(R.id.editAddress);
        btnSave= findViewById(R.id.btnSave);
        editNote = findViewById(R.id.editNote);
        rdgGroupPayment = findViewById(R.id.grpayment);

        //set default checked
        rdbPayment =  findViewById(R.id.rdo_payment1);
        rdbPayment.setChecked(true);
        //Get id selected
        int selectedId = rdgGroupPayment.getCheckedRadioButtonId();
        rdbPayment = findViewById(selectedId);

        editName.setText(Common.currentUser.getName());
        editPhone.setText(Common.currentUser.getPhone());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order = database.getReference("order");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_order.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(editName.length() == 0 || editPhone.length() == 0|| editAddress.length() == 0){
                            Toast.makeText(OrderActivity.this, "vui long nhap thong tin ",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {

                            Order order = new Order(editName.getText().toString(),
                                    editPhone.getText().toString(),editAddress.getText().toString(),
                                    editNote.getText().toString(),rdbPayment.getText().toString());
                            table_order.child(editPhone.getText().toString()).setValue(order);
                            Toast.makeText(OrderActivity.this,"Order successfully !",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }
        });
    }
}

