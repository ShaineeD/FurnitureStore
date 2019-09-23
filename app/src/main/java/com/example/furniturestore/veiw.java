package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class veiw extends AppCompatActivity {
    EditText namee,emaill;
    Member std;
    DatabaseReference reff;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw);
       Intent i = getIntent();
        n= i.getStringExtra("name");

        Toast.makeText(getApplicationContext(), "sdada", Toast.LENGTH_SHORT).show();

        namee=(EditText) findViewById(R.id.editName);
        emaill=(EditText) findViewById(R.id.editMail);
        std=new Member();
      //  Toast.makeText(getApplicationContext(), "ffffddddd", Toast.LENGTH_SHORT).show();






       DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("Member").child(n);
       // veiwbutton.setOnClickListener((veiw) {
                readref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            namee.setText(dataSnapshot.child("name").getValue().toString());
                            Toast.makeText(getApplicationContext(), "ffff", Toast.LENGTH_SHORT).show();

                            emaill.setText(dataSnapshot.child("email").getValue().toString());
                            Toast.makeText(getApplicationContext(), "ssssss", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "no entries to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


         }}

